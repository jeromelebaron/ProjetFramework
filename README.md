#Projet Framework

Mise en place d'un framework web.

##Rappels servlet/JSP

En java web, tout est servlet. Même une page jsp est compilée en servlet par le serveur d'application. Cette phase est appelée **translation**.

Les différents roles du controleur sont les suivants :

- récupérer les données entrées par l'utilisateur
- valider ces données
- convertir les données (les informations envoyées par le protocole http sont en `String`)
- rediriger l'utilisateur au bon endroit