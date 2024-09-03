Feature 1 : le compte bancaire

On souhaite proposer une fonctionnalité de compte bancaire.
Ce dernier devra disposer :

D'un numéro de compte unique (format libre)
D'un solde
D'une fonctionnalité de dépôt d'argent
D'une fonctionnalité de retrait d'argent

La règle métier suivante doit être implémentée :

Un retrait ne peut pas être effectué s'il représente plus d'argent qu'il n'y en a sur le compte

__

Feature 2 : le découvert

On souhaite proposer un système de découvert autorisé sur les comptes bancaires.
La règle métier suivante doit être implémentée :

Si un compte dispose d'une autorisation de découvert, alors un retrait qui serait supérieur au solde du compte est autorisé
si le solde final ne dépasse pas le montant de l'autorisation de découvert

__

Feature 3 : le livret

On souhaite proposer un livret d'épargne.
Un livret d'épargne est un compte bancaire qui :

dispose d'un plafond de dêpot : on ne peut déposer d'argent sur ce compte que dans la limite de ce plafond
ne peut pas avoir d'autorisation de découvert

__

Feature 4 : le relevé de compte

On souhaite proposer une fonctionnalité de relevé mensuel (sur un mois glissant) des opérations sur le compte
Ce relevé devra faire apparaître :

Le type de compte (Livret ou Compte Courant)
Le solde du compte à la date d'émission
La liste des opérations ayant eu lieu sur le compte, triées par date, dans l'ordre antéchronologique
