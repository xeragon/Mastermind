Rapport 1 mastermind
====================

#MVC
---

#### vue
- Une classe MainWindow qui se charge de l'affichage 
- le choix du mode d'affichage est gérer avec le design patern strategy 
  - classe abstraite DisplayMode et les 3 displayMode qui en herite

#### model 
- class Game
    - a comme attribut tout les parametre d'une partie que le joueur choisit au démarage du jeu et une liste de round qu'il initialisera avec les bon parametre de partie 
- class Round
    - a comme attribut une combinaison secrete et un tableau de combinaison (les essais du joueur)
- class Combinaison
    - a comme attribut un tableau de couleur
    - fonction compareTo() = l'objet qui appele la fonction se compare a une combinaison (la combinaison secrète) et renvoie un Hint en lui passant en parametre l'index de toute les couleurs mal placé et toute les couleurs placé a tort ainsi que la taille du Hint (qui est la meme que la taille d'une combinnaison) l'idée est que par défaut le constructeur du Hint produise un tableau qui indique pour chaque case de la combinaison proposé une indication a partir de l'enum HintState (correct,couleur inexistante ou encore couleur mal placé), ce système permet de répondre a tout les modes d'affichages demander par le sujet. le mode facile se contentera d'afficher le tableau tandis que les autres compteront le nombre d'indications et les afficheront chacun de leurs manieres

#### Controller
- GameController
    - se charge de relier modele et vue 
