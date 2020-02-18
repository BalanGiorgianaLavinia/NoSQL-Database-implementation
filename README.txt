						---TEMA 2 POO---

			---BALAN GIORGIANA-LAVINIA	321 CB---
	

		Pentru implementarea acestei teme am creat 9 clase: 

	1.Tema 2: clasa principala care contine metoda main. Aici deschid fisierele
de input si output, parsez inputul prin citirea fiecarei linii pe care o impart
cu split retinand totul in vectorul vec. Primul element al vectorului este comanda
pe care o tratez intr-un switch.

		-daca este vorba despre comanda CREATEDB, retin numele bazei de date, numarul
		de noduri si capacitatea maxima a unui nod, apoi apelez constructorul 
		baze de date pentru a o crea cu parametrii de mai sus.

		-daca este vorba despre comanda CREATE, retin RF-ul si numarul de atribute, 
		dupa care apelez constructorul specific pentru a crea o entitate cu parametrii
		amintiti, dar si vectorul vec pentru a continua parsarea. Dupa crearea 
		entitatii, o adaug in baza de date.

		-daca este vorba despre comanda INSERT, creez o instanta noua si o adaug in 
		baza de date.

		-daca este vorba despre comanda DELETE, retin cheia primara si apelez metoda
		"delete" din baza de date pentru a sterge entitatea cu cheia primara data.

		-daca este vorba despre comanda UPDATE, retin, de asemenea, cheia primara
		si apelez metoda "update" bazei de date pentru a actualiza toate copiile 
		instantei din toate nodurile in care a fost inserata.

		-daca este vorba despre comanda GET, retin, de asemenea, cheia primara si 
		apelez metoda "get" bazei de date pentru a returna instanta cu toate nodurile
		in care se afla.

		-daca este vorba despre comanda SNAPSHOTDB, apelez metoda "snapshot" din 
		baza de date care imi afiseaza toata baza de date in starea actuala.

		-daca este vorba despre comanda CLEANUP, retin timestampul si apelez 
		metoda "cleanup" din baza de date pentru a sterge toate instantele din baza
		de date care au timestampul mai mic decat cel dat.

		La finalul main-ului inchid fisierul de output.




	2.BazaDeDate: aceasta clasa are ca atribute specifice: numele sau, numarul de 
noduri, capacitatea maxima a unui nod, numarul de instante, lista de entitati si 
lista de noduri.

	-Constructorul acestei clase initializeaza atributele de mai sus si creeaza 
No_Nodes noduri cu id-ul corespunzator(numarul de ordine) si capacitatea maxima, 
noduri pe care le adaug in lista de noduri.

	-Metoda "adaugaEntitate" apeleaza metoda "add" pentru a adauga un element in 
lista de entitati.
	
	-Metoda "obtineEntitate" primeste ca parametru numele entitatii. In aceasta 
metoda parcurg lista de entitati si pentru fiecare entitate gasita compar numele 
cu cel cautat. Daca o gasesc atunci o returnez.

	-Metoda "adaugaInstanta" primeste ca parametru o instanta. In aceasta metoda
parcurg toate nodurile din baza de date cat timp mai este nevoie sa inserez instanta
adica cat timp factorul de replicare este mai mare decat 0. Daca nodul respectiv nu 
este plin si nu contine deja instanta, o adaug si decrementez factorul de replicare,
iar numarul total de instante din baza de date il incrementez.

	-Metoda "snapshot" 	primeste ca parametru fisierul de output. Daca nu am nicio
instanta in baza de date afisez mesajul "EMPTY DB". Daca nu, afisez numele nodului,
parcurg lista de noduri, pentru fiecare nod parcurg lista de instante si afisez 
fiecare instanta. Pentru fiecare instanta parcurg lista de atribute specifice si
le afisez.

	-Metoda "get" primeste ca parametrii o entitate, cheia primara si fisierul de
output. In aceasta metoda parcurg lista de noduri, verific daca nodul curent contine
instanta. Daca da, parcurg lista de instante si cand gasesc instanta cu cheia primara 
cautata retin instanta si afisez nodul. Daca nu, ies din metoda dupa afisarea 
mesajului "NO INSTANCE FOUND". Apoi, afisez instanta si parcurg lista sa de atribute
pe care le afisez in functie de tipul lor.

	-Metoda "update" primeste ca parametrii numele unei entitati, cheia primara si
vectorul de parsare si actualizeaza toate copiile instantei din toate nodurile in 
care a fost inserata. In aceasta metoda creez o lista cu atributele si o lista cu
noile lor valori. Parcurg lista de noduri, parcurg lista de instante pentru fiecare
nod, iar daca gasesc instanta cu cheia primara cautata, o actualizez cu metoda update
din clasa Instanta care primeste atributele si noile valori. La final, sortez lista 
de instante din nod.

	-Metoda "delete" primeste ca parametrii numele unei entitati, cheia primara si 
fisierul de output si sterge toate copiile instantei din toate nodurile in care a
fost inserata. In aceasta metoda, parcurg lista de noduri, parcurg lista de instante 
din fiecare nod, iar cand o gasesc, o sterg si setez "deleted" pe true pentru a stii
ca am gasit instanta. Apoi, dupa stergerea instantei, sortez lista de instante din 
nod. Daca la final deleted e setat pe false inseamna ca nu am gasit instanta cautata 
pentru a o sterge si afisez in output mesajul "NO INSTANCE TO DELETE".

	-Metoda "cleanup" primeste ca parametru un timestamp de tip long si sterge toate 
instantele cu timestampul mai mic decat cel dat ca parametru. In aceasta metoda, 
parcurg lista de noduri, pentru fiecare nod parcurg lista de instante. Daca instanta 
curenta are timestampul mai mic, o sterg, sortez lista de instante din nodul curent
si scad numarul total de instante din baza de date.




	3.Nod: aceasta clasa are ca atribute specifice: capacitatea maxima a nodului,
id-ul nodului si lista de instante pentru fiecare nod(un ArrayList cu elemente de
tip instanta).

	-Metoda "estePlin" verifica daca un nod este plin.

	-Metoda "contineInstanta" primeste ca parametru o cheie primara si verifica daca
nodul contine instanta cu cheia primara cautata. Daca lista e goala, ies din metoda,
iar daca nu, parcurg lista de instante si daca gasesc cheia primara cautata returnez 
true.

	-Metoda "adaugaInstanta" primeste ca parametru o instanta si o adauga in lista 
de instante a nodului.



	4.Entitate: aceasta clasa are ca atribute specifice: numele sau, factorul de 
replicare, numarul de atribute ale entitatii, cheia primara si lista de atribute.

	


	5.Instanta: aceasta clasa are ca atribute specifice: numele entitatii, 
timestampul, entitatea, cheia primara si lista de atribute.

	-Constructorul acestei clase initializeaza atributele de mai sus si adauga
fiecare atribut in lista de atribute a instantei.

	-Metoda "clone" cloneaza o instanta cu atributele ei si trateaza exceptia 
specifica.

	-Metoda "update" primeste ca parametrii o lista cu numele atributelor si 
o lista cu valorile lor noi si actualizeaza instanta. La finalul acestei metode, 
setez cheia primara ca fiind primul atribut din lista de atribute.

	-Metoda "compareTo" face o comparatie a timestampurilor.




	6.Atribut: aceasta clasa are ca atribute specifice: numele atributului si 
tipul sau.

	7.StringAtribut: aceasta clasa contine metoda "clone" care cloneaza un atribut 
de tip string prin apelul superclasei.

	8.IntegerAtribut: aceasta clasa are ca atribut specific un atribut de tip intreg.
Aceasta clasa contine metoda "clone" care cloneaza un atribut de tip integer prin 
apelul superclasei.

	9.FloatAtribut: aceasta clasa are ca atribut specific un atribut de tip float.
Aceasta clasa contine metoda "clone" care cloneaza un atribut de tip float prin 
apelul superclasei.