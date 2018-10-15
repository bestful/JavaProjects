
# MLOP_Task1-3
# Компиляция и запуск
Открываем в NetBeans IDE, компилируем. Для использования примера, копируем dist/Solution.jar в my-dist/Solution.jar. 
# Задачи:
## Быстрая сортировка
Дано: список студентов в csv файле (студент, оценка)

Вывести отсортированый (по убыванию) список студентов по оценке в консоль 
## Спиральная матрица
Дано: числа M, N - число строк и столбцов

Вывести спиральную матрицу
## Индексируемый поиск
Дано: текстовые документы в папке

Необходимо проиндексировать документы по частоте, сериализовать частоты в файл и по запросу выводить список документов по убыванию.
# Примеры

## Использование кода
- Змейка

	    public void demoSnake(){
	        int M = scan.nextInt();
	        int N = scan.nextInt();
	        int[][] array = SnakeGen.getMatrix(M, N);
	       
	        int i, j;
	        for(i=1;i<=M;i++){
	            String out = "";
	            for(j=1;j<=N;j++)
	                out += array[i][j] + " ";
	            System.out.println(out);
	        }
	    }
- Класс студентов и компаратор (сортировщик)
	
		class Student{
			public int mark;
			public String last;
			
			Student(String last, int mark){
				this.last = last;
				this.mark = mark;
			}

			String getDescr(){
				return "Last: " + last + ", mark: " + mark;
			}
		}

		class SortByMark implements Comparator<Student>{
			public int compare(Student a, Student b) {
				return a.mark - b.mark;
			}
		}
	
- Сама сортировка

		public void demoSorter() throws FileNotFoundException{
			CsvReader csv = new CsvReader(new FileInputStream(new File(scan.nextLine().trim())) );
			Vector<Student> stud = new Vector();

			int cols = csv.getNumCols();
			int i;

			for(i=0;i<cols;i++){
			stud.add(new Student(csv.get(i,0), Integer.parseInt(csv.get(i, 1))));
			}

			QuickSorter qs = new QuickSorter(stud);
			stud = qs.sort(new SortByMark());

			for(i=0;i<cols;i++){
			System.out.println(stud.get(i).getDescr());
			}
		}
- Индексатор содержит следующие структуры (при использовании необходимо импортировать пакет SearchEngine.Structures)

		// Structure: list of documents
		public class DS extends ArrayList<String> {}
		
		// Structure: map of frequency-documents
		public class FD extends TreeMap<Integer, DS> {
			public FD(){
				super(Collections.reverseOrder());
			}
		}
		
		// Structure: map of word-(frequency-documents)
		public class WFD extends TreeMap<String, FD> {}
- Используем класс SearchEngine в пакете SearchEngine
		SearchEngine se = new SearchEngine();
		query = scan.next();
		
		if(query.equals("import")){
			String dir = scan.next();
			se = new SearchEngine(dir);
			int numWords = se.getIndexer().getWfd().size();
			System.out.format(ots + "Status: OK, %d words%n", numWords);
		}
		else if(query.equals("find")){
			if(se != null){
				String word = scan.next();
				FD fd = se.getOrderedFD(word);
				if(fd == null || fd.isEmpty()){
					System.out.println(ots + "Word is not found");
				}
				else{
					for(Map.Entry me : fd.entrySet()){
						System.out.format(ots + "%s, %d%n", me.getValue(), me.getKey());
					}
				}
			}
			else{
				System.out.println("Please import your documents");
			}
		}

## Использование приложения

	$ cd my-dist
	$ java -jar "Solution.jar"
	> ?
	Commands: import find words exit snake sorter
	> sorter test.csv
	1.csv
	Last: Oleg, mark: -100
	Last: Oksana, mark: 66
	Last: Kolya, mark: 69
	Last: Alena, mark: 88
	Last: Petya, mark: 93
	Last: Petya, mark: 100
	> snake 7 5
	1 2 3 4 5
	20 21 22 23 6
	19 32 33 24 7
	18 31 34 25 8
	17 30 35 26 9
	16 29 28 27 10
	15 14 13 12 11
	> import se
	  Status: OK, 2748 words
	> find near
	  [edwards.txt], 3
	> find neightbour
	  Word is not found
	> find knn
	  [knn.txt], 7
	> find regression
	  [knn.txt, supervised_learning.txt], 12
	  [usupervised_learning.txt], 1
	> import ff
	  Status: OK, 52800 words
	> find heaven
	  [The Rise of the Kurogan.txt], 29
	  [Living Behind a Mask.txt], 17
	  [The Eternal Chimera.txt], 15
	  [A Fishcake's affair.txt, Naruto the Arm User.txt], 13
	  [Juubi of Souls.txt, Naruto the Silver Fox.txt, Resurrection of the Uzumaki Clan.txt], 9
	  [Demonic Nemesis.txt, Kaze No Kami.txt, Kyuubichan.txt, The Summon Master.txt], 8
	  [Kyuubi's undying love.txt, The Red String of Fate.txt], 7
	  [Lost Paradise.txt, Love Sick.txt, Naruto ZX.txt, She Came from Lightyears Away.txt, Ten Thousand Fists.txt], 5
	  [Naruto The New Knight Rider.txt], 4
	  [A Winter's Promise.txt, Ascension of the Kitsune.txt, Changes Has It's Reasons.txt, Chi Nikuyoku.txt, Demon Love.txt, Fall of an angel.txt, ICHIBI NO KITSUNE.txt, Kits!.txt, My loving demons.txt, naruto legacy.txt, New Friends.txt, Nine Tales Of Naruto.txt, Object of My Desire.txt, Oretachi no Sadame.txt, Silent Storm.txt, Tales of A Golden Fox.txt, The Village of Chakra.txt, To Love My Brother.txt, What happens in heaven.txt], 3
	  [between a wolf and a fox.txt, Cloak of the Forbidden End.txt, CLONE SHARINGAN.txt, Demonic Requiem.txt, Grim Reaper Kyo.txt, Hey There Foxy!.txt, It Takes One To Know One.txt, Kitsune no Kyuubi, Naruto no Kitsune.txt, kitsune's daughter.txt, KitsuneNin Sage!.txt, Kyuubi's Games.txt, Namikaze Legend.txt, Naruto and His Guardian Vixens.txt, Naruto the Battousai.txt, Naruto the Kyudaime Overlord.txt, Naruto The One.txt, Redemption of A Lone Wolf.txt, Say What!.txt, Shadow Ship.txt, The Art of Healing.txt, The Dark Road to the Otherside.txt, The Idiot's Awakening.txt], 2
	  [A Forbiden Life leads to a Forbiden Love.txt, A Loving Heart, A Body of Steel.txt, Aria of the Moonless Night.txt, Battlesuit K.txt, Breaking the Kitsune.txt, Child of the Fox!.txt, Darkness Rising.txt, Demon Storm, Shinobi Rain.txt, Early Banishment.txt, Even Demons Can Love.txt, Feel the Beat.txt, Foxes and Wolves, Humans and Demons.txt, Frozen Darkness.txt, Guardian DeathAngel.txt, I Warned You.txt, Kage No Naruto.txt, Kitsune Kanshisha or Fox Guardian.txt, Kitsune Thief Naruto.txt, Kyuubi's Claim.txt, Maelstrom Ninja Scrolls.txt, Mercy Has Its Price.txt, Mother's love.txt, My new life.txt, NamikazeCest.txt, Nariko, My Sister.txt, Naruto and his Foxy Demon.txt, Naruto and the Timeskip.txt, Naruto Water Chronicles.txt, Naruto's Last Straw.txt, October 10th.txt, Pain of the Truth.txt, Purpose.txt, Red Rubies, Tainted Sapphire.txt, Rise of the Supernovas.txt, Savin' Me.txt, Shinobi Farmer.txt, Shrine in the Fog.txt, Story of the Ten Tailed Wolf.txt, Taming of a Kitsune.txt, The Demonic Siblings.txt, The Fox and and the Ninja.txt, The Itinerant Trader.txt, The Shifter.txt, The True Naruto Uzumaki Namikaze.txt, There and Back Again, a Shinobi's Tale.txt, Through Demon Eyes.txt, Turning Over a New Leaf.txt], 1
	> exit

## TODO
SearchEngine:
 - написать документацию к классам
 - немного рефакторинга
 - реализовать возможность поиска по нескольким словам
