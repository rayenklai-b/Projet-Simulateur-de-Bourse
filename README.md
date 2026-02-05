# Simulateur de Bourse Multi-Threads

## 📋 Description
Simulation d'un carnet d'ordres (`OrderBook`) partagé entre plusieurs traders concurrents.

## ⚠️ Problème de Concurrence

### Sans `synchronized`

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
