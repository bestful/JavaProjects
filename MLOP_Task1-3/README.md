# MLOP_Task1-3
Задачи:
 1. Быстрая сортировка студентов из csv файла по оценке (Студент, оценка)
 2. Выдача спиральной матрицы
 3. Ранжирование документов по слову

## Пример

	$ java -jar "Solution.jar"
	> ?
	Commands: import find words exit task1 task2
	> task1
	1.csv
	Last: Oleg, mark: -100
	Last: Oksana, mark: 66
	Last: Kolya, mark: 69
	Last: Alena, mark: 88
	Last: Petya, mark: 93
	Last: Petya, mark: 100
	> task2
	7 5
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