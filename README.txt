323CD Necula Andreea-Teodora

Proiect Santa Claus is coming to ACS students

	Programul incepe cu parsarea datelor de input (main/Main) si continua
cu desfasurarea actiunii (main/GoGoGo). Output-ul consta in afisarea listelor
de copii din toti anii denumit fileio/FinalVersion.
	Desfasurarea propriu-zisa a rundelor incepe in GoGoGo in functia action
unde se va crea si clasa de output FinalVersion. Se lucreaza pe lista de copii
de input si se vor elimina copiii care au implinit 18 ani. In functie de 
categoria de varsta in care sunt incadrati se calculeaza scorul fiecarui copil
si se adauga bonusul dupa caz. Se aplica elfii PINK si BLACK care vor mari sau
vor micsora bugetului copilului. 
	In continuare se aplica o strategie pentru a 
determina modul in care trebuie ordonati copiii inainte de a primi cadourile.

IdStrategy - se ordoneaza copiii dupa id si in cadrul acestei clase se 
determina pentru fiecare copil cel mai ieftin cadou disponibil din 
fiecare categorie preferata in limita bugetului alocat.
NiceScoreStrategy - ordoneaza copiii dupa scorul de cumintenie, al doilea 
criteriu de comparatie fiind id-ul. Se determina in mod similar cel mai 
ieftin cadou disponibil din fiecare categorie preferata in limita bugetului
alocat.
NiceScoreCityStrategy - distribuie copiii descrescator in functie de media 
oraselor in care se afla, al doilea criteriu fiind id-ul. In acest sens
am creat o clasa CityWrapper pentru a retine fiecare oras si scorul 
corespunzator lui. Se aloca o lista cu elemente de acest tip si vor fi
sortate descrescator in functie de media obtinuta, daca doua orase au
aceeasi medie se va lua in considerare sortarea ascendenta dupa id.
   
Dupa distribuirea cadourilor tuturor copiilor, acei copii care au asociat
elful YELLOW si nu au primit niciun cadou in acel an vor primi cel mai
ieftin cadou din prima categorie preferata daca acesta mai exista in lista
de cadouri a mosului.
 
Design patterns utilizate:
- Singleton - pentru a crea o singura instanta a clasei care executa actiunea 
	      principala
- Strategy  - pentru a determina modul in care trebuie sortati copiii inainte de
              a-si primi cadourile
- Factory   - pentru a crea o strategie de ordonare a copiilor
- Builder   - pentru a crea instante copil daca au sau nu bonus