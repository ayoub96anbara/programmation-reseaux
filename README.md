# Des notes programmation-reseaux

cours Pr mohamed youssfi:<br>
https://www.youtube.com/watch?v=NoJD1MOyVBI&ab_channel=mohamedYoussfi

## Part 1,2,3
si vous avez une application 1 qui se trouve dans une machine 1, qu'il a besion de
communiquer avec une autre application 2 qui se trouve dans une autre machine 2, 
il doit utiliser un protocole reseau: TCP ou UDP
pour utiliser TCP, on utilise une api Socket
- pour transfere des donnees lourds(vedio, files), il est recommande d'utilser UDP.
- d'une maniere general, on utilise TCP 
une application distribues: est une application qui a une partie se trouve dans une
 machine, et autre dans autre machine, au lieu de passer par des socket, on passe par des
 Middlewares.
 ex des Middlewares:
 - Objects Distribues: RMI(pour java sauf), COBRA (Multi langages)
 - Orientes Services: SOAP, REST (Web Service)
 - Orientes Messages (Modeles de Communication Asynchrone): JMS, AMQP, Stomp, MQTT
 => whatsapp utilise cette derniere 
 
 --cote serveur
 pour cree un serveur avec java: new ServerSocket(PORT); 
 ServerSocket est objet qui permet d'ouvrire un service d'ecoute, et qui attent q'un client puisse demander une connexion,
 il dispose une methode accept, cette derniere est une instruction bloquante, elle attent la demande d'une connexion, elle
 retourne un objet Socket, qui contient tout les informations sur le clients
 --cote client
 on doit creer une socket: new Socket(IP,PORT);
 
 un server : c'est pas une machine, est une application parceque dans une meme machine on peut demarre plusieurs serveur 
 la connexion ==> la creation d'un socket cote server et un autre socket cote client
 
 on peut envoyer des octes, des chaines de caracteres, des objets (Serializable objects).
 pour envoyer des objets (Serialisation et Deserialisation):
 
 
 ## Construire un vrai serveur Part 3( 17:06 min)
 ### Serveur Multi-Threads avec IO Bloquantes
 on utilise des Threads.
 un vrai serveur est capable de communiquer avec plusieurs clients en meme temps, pour cela, il faut:
 - le serveur puisse attendre une connexion a tout moment
 - pour chq connexion,il faut cree un nouveau thread associe a la socket du client connecte, puis attendre a nouveau une
 nouvelle connexion
 - Le thread cree doit s'occuper des operations d'entrees-sorties (read/write) pour communiquer avec le client independ-
 mmant des autres activites du serveur.  
 
 to test application, we create client class or we can use telnet:  $ telnet localhost 1234
 
 
 
 

 
 
