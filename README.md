
Bibliothèque graphique
Le projet est à réaliser avec la bibliothèque graphique JavaFX. Une présentation light de la librairie est disponible ici : https://gamedevelopment.tutsplus.com/tutorials/introduction-to-javafx-for-game-development--cms-23835 Il n'est pas autorisé de gérer l'affichage graphique par un autre moyen. 

Déroulement du jeu
Illustration de l'aire de jeux
La galaxie contient :

    une planète d'origine par joueur;
    un ensemble de planètes neutres;
    des zones "libres" (i.e. l'espace).


Les acteurs du jeu sont des vaisseaux avec des caractéristiques fixées (vitesse, temps de production, puissance d'attaque) - produits par les planètes contrôlées par un joueur.

Chaque joueur possède initialement une planète. Toutes les planètes - exceptées les neutres - produisent des vaisseaux. Le taux de production de chaque planète est fixé à la création de cette dernière. À tout moment du jeu, seules les planètes contrôlées par un joueur peuvent produire des vaisseaux pour ce joueur.

Le jeu consiste à déplacer les vaisseaux de planète en planète. Pour ce faire, le joueur sélectionne à la souris une planète d'origine et une planète de destination en faisant un cliquer-glisser. Une fois l'ordre émis, une partie (en nombre contrôlable via un pourcentage) des vaisseaux décolle de la planète origine pour aller sur la planète de destination. Pendant que les vaisseaux se déplacent à l'écran, les planètes continuent de produire de nouveaux vaisseaux.

Lorsqu'un vaisseau atteint une planète,

    soit la planète appartient au joueur qui a déplacé le vaisseau et il s'agit juste d'un mouvement de troupes (sa réserve de vaisseaux est alors incrémentée de 1);
    soit elle n'appartient pas au même joueur et c'est une attaque "kamikaze" (sa réserve de vaisseaux est alors décrémentée de la puissance d'attaque du vaisseau, fixée par défaut à 1);
    si la planète est neutre ou contrôlée par un autre joueur et si sa réserve est nulle lors de l'attaque, la planète est conquise et appartient au joueur qui a lancé l'attaque. Elle se met à produire des vaisseaux du même type que celui qui en a permis la conquête.

Un joueur perd quand il ne contrôle plus de planète. Le jeu se termine lorsqu'il n'y a plus qu'un seul joueur.
Contraintes du jeu à respecter
Pour une première version fonctionnelle du jeu, les vaisseaux auront tous les mêmes caractéristiques.

Pour simplifier la gestion des collisions et des contournements, les planètes et vaisseaux peuvent avoir une forme carrée, mais si vous le souhaitez, vous pouvez utiliser d'autres formes. La position et la taille des planètes ainsi que le type des vaisseaux (vitesse, temps pour leur production, puissance d'attaque) qu'elles produisent au départ sont déterminés de façon aléatoire au début de la partie. Les planètes doivent bien sûr être bien plus grosses qu'un vaisseau (au moins 4 fois la taille d'un vaisseau spatial). N'oubliez pas de respecter une distance minimale entre les planètes.

Toutes les planètes peuvent produire et envoyer des vaisseaux à l'exception des planètes neutres qui, jusqu'à leur conquête, ne peuvent qu'utiliser leur réserve de vaisseaux à titre défensif. À un instant donné, toute planète ne peut produire qu'un seul type de vaisseaux.

L'envoi de vaisseaux doit être fait par diffusion autour de la planète émettrice, c'est-à-dire que les vaisseaux ne doivent pas se recouvrir au décollage. Par conséquent, le décollage d'un grand nombre de vaisseaux d'une petite planète peut nécessiter plusieurs vagues de décollage. Les vaisseaux envoyés par un unique ordre compose un escadron. Il doit être possible de changer facilement la destination d'un escadron qui n'a pas encore atteint sa destination.

Lors des déplacements, les collisions entre les vaisseaux ne doivent pas être gérées, en d'autres termes il peut y avoir plus d'un vaisseau à une même position. Par contre, un vaisseau devant atteindre une planète donnée ne devra ni survoler, ni rentrer en collision avec une autre planète. Pour ce faire, vous devrez mettre en place un algorithme de déplacement permettant le contournement des planètes non visées.

Votre application minimale ne contiendra que des planètes et vaisseaux d'un seul type (même forme, mais la taille peut varier).  Il n'y aura pas de bonus pour l'utilisation d'images ou d'autres formes géométriques pour la version minimale.
Mode d'interactions sur le jeu
Il vous est demandé de mettre en place une interaction à la souris avec le jeu qui peut s'accompagner de l'utilisation simultanée des touches gérées par javafx (par exemple CTRL, SHIFT, ALT-GR - cf classe MouseEvent). Vous avez également la possibilité d'interagir avec le jeu par l'utilisation du clavier (cf classe KeyCode et l'exemple fourni).

Pour s'entraîner sur la première séance (semaine 46)

Dans l'activité VPL suivante, une version de test nommée Alien Versus Pineapple est fournie pour comprendre le fonctionnement de javafx et des interactions.

    Récupérez l'ensemble des fichiers du projet
    Mettez en place pour environnement de développement en configurant un projet Eclipse pour le projet (à noter que les répertoires "src" et "resources" doivent être configuré comme des répertoires sources)
        Projet -> Préférences -> Java Build Path ; dans l’onglet « Sources » , faire « Ajouter folder » et sélectionner « ressources » 
    Exécutez le programme et constatez son fonctionnement : clic sur l'écran, appui sur les flèches directionnelles
    Modifiez le programme pour qu'un appui sur la touche "a" produise 10 nouveaux ananas répartis aléatoirement sur la scène
    Modifiez le programme pour qu’un clic de souris lorsque CTRL est pressée produise un nouvel ananas à cette position au lieu d’y déplacer le vaisseau


Une fois le fonctionnement de la bibliothèque compris, créez votre propre projet et commencez par créer des versions minimales des classes planètes et vaisseaux, que vous afficherez à l’écran après une initialisation aléatoire.
Implémentez ensuite le déplacement naïf des vaisseaux.
Poursuivez en implémentant petit à petit les fonctionnalités du jeu.

Alien vs Pineapple Virtual programming lab

Travail minimal à effectuer (pour avoir 12)
Il vous est demandé de fournir une implémentation fournissant au moins les fonctionnalités suivantes:

    contrôle du jeu à la souris+clavier ;
    le déplacement de troupes de planète en planète -- en mode attaque et défense ;
    contrôle de la quantité de troupes envoyées par escadron -- ce contrôle se fait via un pourcentage que le joueur peut changer facilement au clavier ou à la souris avant chaque mouvement de troupes ;
    fournir une Intelligence Artificielle (très simple, au pire aléatoire, ce n'est pas l'objectif de ce projet ni du cours de programmer une IA digne de ce nom) permettant de jouer contre l'ordinateur ou de faire jouer l'ordinateur contre lui-même. Il n'est pas demandé de pouvoir gérer plus d'un joueur "humain" mais votre implémentation ne doit pas l'empêcher.
    la sauvegarde/restauration d'une partie en cours (voir ObjectOutputStream et ObjectInputStream).

Pour aller plus loin (et avoir plus de 12)
Si vous le souhaitez, vous pourrez fournir une seconde version (attention, cette dernière est en supplément de la version minimale et doit être composée de ses propres fichiers). Vous pouvez apporter d'autres fonctionnalités rendant votre jeu plus intéressant. Ces ajouts ne sauraient compenser des manques dans les fonctionnalités attendues et sont, par conséquent, des bonus non compensatoires du minimum attendu.

Quelques idées en vrac à s'approprier :

    Utiliser des planètes et vaisseaux de formes, tailles différentes et plus généralement de caractéristiques différentes. La taille et la forme doivent indiquer des caractéristiques différentes des objets (un vaisseau de forme triangulaire et un vaisseau carré ne doivent pas avoir les mêmes caractéristiques).
    Proposer la production et l'exploitation de plusieurs types de vaisseaux à caractéristiques différentes (par exemple vitesse, temps de production, puissance d'attaque). Si vous donnez la possibilité de changer le type de production d'une planète; vous devrez assurer qu'à tout moment la planète en question ne contient pas dans sa réserve plusieurs types de vaisseaux (par exemple en détruisant sa réserve au préalable, ou en expulsant les vaisseaux du type actuel, ou autre),
    Gestion de plus de 2 joueurs (mais toujours un seul humain),
    Des vaisseaux pirates (sans propriétaire et frappant au hasard),
    Des planètes malades (dont la production n'est pas constante),
    ...


Déclaration des binômes Auto-sélection de groupe (en test)

Les projets devant être effectués en binôme, il vous faut définir vos binômes via cette activité.
Remise du Projet Virtual programming lab

Conditions de rendu
La remise du projet inclus les fichiers sources (via VPL sur moodle, comme lors du TP noté) ainsi qu'une documentation.

Fichiers sources
L'ensemble des fichiers du projet sont à déposer sur VPL via cette activité. La structure de votre dépôt doit impérativement être la suivante :

        Répertoire 'src_basic' avec les sources de la version de base du jeu ;
        Répertoire 'src_advanced' avec les sources d'une version améliorée du jeu si vous en avez fait une. N'oubliez pas de bien lister les ajouts et modifications effectuées par rapport à la version de base du jeu dans la documentation ;
        Répertoire 'resources' contenant les images et sons que votre jeu utilise (séparés en deux sous-répertoires 'images' et 'sounds').

Pour déposer un fichier, vous l'ajouter via le bouton puis vous le déplacer dans le bon répertoire en modifiant son chemin en appuyant sur le bouton . Par exemple, pour ajouter le fichier Sprite.java, vous le renommer en Projet/src_basic/alien/Sprite.java (ce qui aura pour effet de créer les répertoires intermédiaires).
Par ailleurs, votre code devra être commenté de manière à ce qu’il soit possible de générer une javadoc. Celle-ci devra documenter toutes les méthodes et tous les attributs de vos classes en décrivant leur rôle et, pour les méthodes, le rôle de leurs arguments et valeur de retour. Respectez le format javadoc.


Documentation
La documentation du projet est à écrire à fournir sous la forme d'un fichier README à remettre également via VPL. La documentation doit comporter au minimum les éléments suivants :

    Mode d'emploi de votre programme (lancement, options éventuelles de la ligne de commande)
    Règle du jeu complète
    Description claire et précise des fonctionnalités implémentées, des points à améliorer et les éventuels dysfonctionnements connus. 

Attention, la javadoc et la documentation du projet sont deux choses distinctes.

Notation

    Cas de 0 sans aucune correction:
        projet non effectué en binôme (2 personnes !) sans l'accord préalable de l'intervenant de TD ;
        projet ne respectant pas le découpage en dossiers explicité ci-dessus.
        absence de README
    Base de la notation :
        La version de base du jeu fonctionnelle et bien documentée vaut 12 ;
        la propreté et la lisibilité du code auront un poids très important dans la note ;
        la qualité de la javadoc qui représente l'architecture que vous aurez définie (interfaces, classes abstraites, concrètes ...)  ;
        la réutilisabilité et donc la factorisation de votre code (par exemple peut-on facilement créer des planètes triangulaires, ... sans tout refaire dans votre code ?) ;
        la présence de code inutile ;
        les différents écrits et, par conséquent, l'orthographe et la grammaire !
