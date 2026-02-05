# Simulateur de Bourse Multi-Threads

## 📋 Description
Simulation d'un carnet d'ordres (`OrderBook`) partagé entre plusieurs traders concurrents.

## ⚠️ Problème de Concurrence

### Sans `synchronized` (branche `phase0-unsafe-race-condition`)

Lorsque plusieurs threads accèdent simultanément à `price` sans synchronisation :

**Code vulnérable :**
```java
public double getPrice() {
    return price;
}

public void updatePrice(double newPrice) {
    this.price = newPrice;
}
```

**Problèmes :**

1. **Lost Updates** (mises à jour perdues)
```
Thread-1 lit price = 50.0
Thread-2 lit price = 50.0  ← lit la même valeur !
Thread-1 écrit price = 51.0
Thread-2 écrit price = 49.0  ← écrase la valeur de Thread-1
```
Résultat : la mise à jour de Thread-1 est perdue.

2. **Visibility** (visibilité)
```
Thread-1 écrit price = 55.0
Thread-2 lit price = 50.0  ← voit encore l'ancienne valeur (cache CPU)
```

**Exemple de sortie incohérente :**
```
Trader-1 saw price 50.0 -> updated to 51.2
Trader-2 saw price 50.0 -> updated to 49.3  ⚠️ même valeur lue
Trader-3 saw price 50.0 -> updated to 50.8  ⚠️ encore 50.0 !
Trader-4 saw price 49.3 -> updated to 48.1
```

---

### Avec `synchronized` ✅ (branche actuelle : `phase1-synchronized-solution`)
```java
public synchronized double getPrice() {
    return price;
}

public synchronized void updatePrice(double newPrice) {
    this.price = newPrice;
}
```

**Garanties :**
- ✅ Un seul thread à la fois accède aux méthodes
- ✅ Toutes les modifications sont visibles immédiatement
- ✅ Aucune mise à jour perdue

**Sortie cohérente :**
```
Trader-1 saw price 50.0 -> updated to 51.2
Trader-2 saw price 51.2 -> updated to 50.5  ✅ voit la dernière valeur
Trader-3 saw price 50.5 -> updated to 51.0  ✅ cohérent
```

## 🚀 Exécution
```bash
javac com/trading/*.java
java com.trading.Main
```

## 📚 Branches
- `phase0-unsafe-race-condition` : version sans synchronisation (race conditions)
- `phase1-synchronized-solution` : version sécurisée avec synchronized