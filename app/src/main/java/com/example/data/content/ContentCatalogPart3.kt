package com.example.data.content

import com.example.data.model.ActivityType
import com.example.data.model.DifficultyLevel
import com.example.data.model.LearningActivity
import com.example.data.model.MapLandmark
import com.example.data.model.WorldId

object ContentCatalogPart3 {
    val mapLandmarks: List<MapLandmark> = listOf(
        MapLandmark(
            id = "lm_dhaka", name = "National Parliament & Sundarbans", country = "Bangladesh",
            continent = "Asia", capital = "Dhaka", flagEmoji = "🇧🇩",
            funFact = "Bangladesh is home to the Sundarbans, the world's largest mangrove forest, and Royal Bengal Tigers!",
            latitude = 23.8103, longitude = 90.4125,
            quizQuestion = "What is the capital city of Bangladesh?",
            quizOptions = listOf("Dhaka", "Chattogram", "Sylhet", "Rajshahi"),
            quizCorrectAnswer = "Dhaka"
        ),
        MapLandmark(
            id = "lm_tokyo", name = "Mount Fuji", country = "Japan",
            continent = "Asia", capital = "Tokyo", flagEmoji = "🇯🇵",
            funFact = "Mount Fuji is a tall snow-capped mountain in Japan, the Land of the Rising Sun!",
            latitude = 35.6762, longitude = 139.6503,
            quizQuestion = "Which country has Tokyo as its capital?",
            quizOptions = listOf("Japan", "India", "Brazil", "Egypt"),
            quizCorrectAnswer = "Japan"
        ),
        MapLandmark(
            id = "lm_cairo", name = "Great Pyramids of Giza", country = "Egypt",
            continent = "Africa", capital = "Cairo", flagEmoji = "🇪🇬",
            funFact = "Ancient Egyptians built massive stone pyramids over 4,500 years ago near the Nile River!",
            latitude = 30.0444, longitude = 31.2357,
            quizQuestion = "Where are the Great Pyramids located?",
            quizOptions = listOf("Egypt", "France", "Canada", "Australia"),
            quizCorrectAnswer = "Egypt"
        ),
        MapLandmark(
            id = "lm_paris", name = "Eiffel Tower", country = "France",
            continent = "Europe", capital = "Paris", flagEmoji = "🇫🇷",
            funFact = "The Eiffel Tower in Paris is made of iron and sparkles with lights at night!",
            latitude = 48.8566, longitude = 2.3522,
            quizQuestion = "In which city stands the Eiffel Tower?",
            quizOptions = listOf("Paris", "London", "Rome", "Dhaka"),
            quizCorrectAnswer = "Paris"
        ),
        MapLandmark(
            id = "lm_london", name = "Big Ben", country = "United Kingdom",
            continent = "Europe", capital = "London", flagEmoji = "🇬🇧",
            funFact = "Big Ben is the nickname of the giant bell inside the famous clock tower in London!",
            latitude = 51.5072, longitude = -0.1276,
            quizQuestion = "What is the capital of the United Kingdom?",
            quizOptions = listOf("London", "Madrid", "Tokyo", "Ottawa"),
            quizCorrectAnswer = "London"
        ),
        MapLandmark(
            id = "lm_nyc", name = "Statue of Liberty", country = "United States",
            continent = "North America", capital = "Washington, D.C.", flagEmoji = "🇺🇸",
            funFact = "The Statue of Liberty holds a glowing torch in New York Harbor!",
            latitude = 40.6892, longitude = -74.0445,
            quizQuestion = "Which continent is the United States in?",
            quizOptions = listOf("North America", "Africa", "Europe", "Australia"),
            quizCorrectAnswer = "North America"
        ),
        MapLandmark(
            id = "lm_rio", name = "Amazon Rainforest & Rio", country = "Brazil",
            continent = "South America", capital = "Brasilia", flagEmoji = "🇧🇷",
            funFact = "Brazil has the giant Amazon Rainforest full of colorful macaws and monkeys!",
            latitude = -22.9068, longitude = -43.1729,
            quizQuestion = "Which South American country is famous for the Amazon Rainforest?",
            quizOptions = listOf("Brazil", "Japan", "Egypt", "Italy"),
            quizCorrectAnswer = "Brazil"
        ),
        MapLandmark(
            id = "lm_sydney", name = "Sydney Opera House", country = "Australia",
            continent = "Australia", capital = "Canberra", flagEmoji = "🇦🇺",
            funFact = "Australia is both a country and a continent, home to kangaroos and koalas!",
            latitude = -33.8688, longitude = 151.2093,
            quizQuestion = "Which fluffy animal carries babies in a pouch in Australia?",
            quizOptions = listOf("Kangaroo", "Penguin", "Polar Bear", "Camel"),
            quizCorrectAnswer = "Kangaroo"
        )
    )

    val gkActivities: List<LearningActivity> = listOf(
        LearningActivity(
            id = "gk_1", worldId = WorldId.GK, title = "Pride of Bangladesh",
            topicTag = "Bangladesh", type = ActivityType.MULTIPLE_CHOICE, difficulty = DifficultyLevel.BEGINNER,
            prompt = "What is the National Flower of Bangladesh?",
            promptBn = "বাংলাদেশের জাতীয় ফুল কোনটি?",
            speechText = "What is the National Flower of Bangladesh?",
            options = listOf("Shapla (Water Lily) 🪷", "Rose 🌹", "Sunflower 🌻", "Tulip 🌷"),
            correctAnswer = "Shapla (Water Lily) 🪷",
            hint = "It floats gracefully on rivers and ponds!",
            explanation = "Shapla (White Water Lily) is the National Flower of Bangladesh!",
            visualEmoji = "🇧🇩"
        ),
        LearningActivity(
            id = "gk_2", worldId = WorldId.GK, title = "Bangladesh National Animal",
            topicTag = "Bangladesh", type = ActivityType.PICTURE_MATCH, difficulty = DifficultyLevel.BEGINNER,
            prompt = "Which majestic striped cat lives in the Sundarbans and is Bangladesh's national animal?",
            promptBn = "বাংলাদেশের জাতীয় পশু কোনটি?",
            speechText = "What is the national animal of Bangladesh?",
            options = listOf("Royal Bengal Tiger 🐯", "Elephant 🐘", "Giraffe 🦒", "Panda 🐼"),
            correctAnswer = "Royal Bengal Tiger 🐯",
            hint = "Orange fur with dark stripes!",
            explanation = "Roar! The Royal Bengal Tiger lives in the Sundarbans!",
            visualEmoji = "🐯"
        ),
        LearningActivity(
            id = "gk_3", worldId = WorldId.GK, title = "Identify the Flag: Bangladesh",
            topicTag = "Countries & Flags", type = ActivityType.PICTURE_MATCH, difficulty = DifficultyLevel.BEGINNER,
            prompt = "Which country has a green flag with a bright red circle in the middle? 🇧🇩",
            promptBn = "সবুজ জমিনে লাল সূর্য কোন দেশের পতাকা?",
            speechText = "Which country flies a green flag with a red sun circle?",
            options = listOf("Bangladesh", "Japan", "Canada", "Brazil"), correctAnswer = "Bangladesh",
            hint = "Green stands for lush nature and red for the rising sun!",
            explanation = "Yes! That is the proud flag of Bangladesh!",
            visualEmoji = "🇧🇩"
        ),
        LearningActivity(
            id = "gk_4", worldId = WorldId.GK, title = "Identify the Flag: Japan",
            topicTag = "Countries & Flags", type = ActivityType.MULTIPLE_CHOICE, difficulty = DifficultyLevel.BEGINNER,
            prompt = "Which country's flag is white with a single red circle? 🇯🇵",
            promptBn = "সাদা পতাকার মাঝে লাল বৃত্ত কোন দেশের পতাকা?",
            speechText = "Which country has a white flag with a red circle?",
            options = listOf("Japan", "France", "Egypt", "Australia"), correctAnswer = "Japan",
            hint = "It is called the Land of the Rising Sun!",
            explanation = "Japan! Tokyo is its bustling capital.",
            visualEmoji = "🇯🇵"
        ),
        LearningActivity(
            id = "gk_5", worldId = WorldId.GK, title = "Match Capitals & Countries",
            topicTag = "Capitals", type = ActivityType.PAIR_MATCH, difficulty = DifficultyLevel.EXPLORER,
            prompt = "Match each country with its capital city!",
            promptBn = "দেশের সাথে রাজধানীর নাম মেলাও!",
            speechText = "Match each country to its capital city.",
            correctAnswer = "ALL_MATCHED",
            hint = "Bangladesh → Dhaka, France → Paris, UK → London!",
            explanation = "World geography champ!",
            visualEmoji = "🏛️",
            matchPairs = listOf("Bangladesh 🇧🇩" to "Dhaka", "France 🇫🇷" to "Paris", "UK 🇬🇧" to "London")
        ),
        LearningActivity(
            id = "gk_6", worldId = WorldId.GK, title = "Seven Continents",
            topicTag = "Continents", type = ActivityType.MULTIPLE_CHOICE, difficulty = DifficultyLevel.EXPLORER,
            prompt = "How many continents are there on planet Earth?",
            promptBn = "পৃথিবীতে কয়টি মহাদেশ আছে?",
            speechText = "How many continents are on planet Earth?",
            options = listOf("5", "6", "7", "8"), correctAnswer = "7",
            hint = "Asia, Africa, North America, South America, Antarctica, Europe, Australia!",
            explanation = "7 continents make up our wonderful world!",
            visualEmoji = "🗺️"
        ),
        LearningActivity(
            id = "gk_7", worldId = WorldId.GK, title = "Famous Landmark: Egypt",
            topicTag = "Famous Landmarks", type = ActivityType.MULTIPLE_CHOICE, difficulty = DifficultyLevel.EXPLORER,
            prompt = "Where can explorers visit the ancient Pyramids?",
            promptBn = "প্রাচীন পিরামিড কোন দেশে অবস্থিত?",
            speechText = "In which country can you visit the ancient Pyramids?",
            options = listOf("Egypt 🇪🇬", "Japan 🇯🇵", "Brazil 🇧🇷", "Canada 🇨🇦"),
            correctAnswer = "Egypt 🇪🇬",
            hint = "They stand near the desert sands of Cairo!",
            explanation = "Ancient Egypt built the Great Pyramids!",
            visualEmoji = "🐪"
        ),
        LearningActivity(
            id = "gk_8", worldId = WorldId.GK, title = "Transportation Sort",
            topicTag = "Transportation", type = ActivityType.DRAG_SORT, difficulty = DifficultyLevel.BEGINNER,
            prompt = "Sort vehicles by where they travel: Land, Water, or Sky!",
            promptBn = "যানবাহন কোথায় চলে আলাদা করো!",
            speechText = "Sort each vehicle into Land or Sky.",
            correctAnswer = "ALL_SORTED",
            hint = "Airplanes fly in the sky, Trains roll on tracks!",
            explanation = "All aboard! Great transport sorting!",
            visualEmoji = "🚂",
            buckets = listOf("Land / Water", "Sky"),
            dragItems = listOf("Train 🚂" to "Land / Water", "Airplane ✈️" to "Sky", "Boat ⛵" to "Land / Water", "Helicopter 🚁" to "Sky")
        ),
        LearningActivity(
            id = "gk_9", worldId = WorldId.GK, title = "Who Am I? Community Helpers",
            topicTag = "Professions", type = ActivityType.MULTIPLE_CHOICE, difficulty = DifficultyLevel.BEGINNER,
            prompt = "Who am I? I wear a white coat, use a stethoscope, and help sick people feel better!",
            promptBn = "আমি কে? স্টেথোস্কোপ দিয়ে রোগীদের সুস্থ হতে সাহায্য করি!",
            speechText = "Who uses a stethoscope to help sick people feel better?",
            options = listOf("Doctor 🩺", "Chef 👩‍🍳", "Pilot ✈️", "Painter 🎨"),
            correctAnswer = "Doctor 🩺",
            hint = "You visit them at a clinic or hospital!",
            explanation = "Yes! Doctors keep our communities healthy!",
            visualEmoji = "🩺", isVocabulary = true
        ),
        LearningActivity(
            id = "gk_10", worldId = WorldId.GK, title = "Who Am I? Firefighter",
            topicTag = "Professions", type = ActivityType.MULTIPLE_CHOICE, difficulty = DifficultyLevel.BEGINNER,
            prompt = "Who am I? I drive a bright red truck with a ladder and put out fires safely!",
            promptBn = "লাল গাড়ি চালিয়ে কে আগুন নেভাতে আসে?",
            speechText = "Who drives a red fire truck and puts out fires?",
            options = listOf("Firefighter 🚒", "Farmer 🌾", "Baker 🥐", "Musician 🎸"),
            correctAnswer = "Firefighter 🚒",
            hint = "They wear helmets and use water hoses!",
            explanation = "Brave firefighters keep everyone safe!",
            visualEmoji = "🚒", isVocabulary = true
        ),
        LearningActivity(
            id = "gk_11", worldId = WorldId.GK, title = "Sports Match",
            topicTag = "Sports", type = ActivityType.PAIR_MATCH, difficulty = DifficultyLevel.BEGINNER,
            prompt = "Match each sport to the equipment used to play it!",
            promptBn = "খেলার সাথে খেলার সামগ্রী মেলাও!",
            speechText = "Match each sport with its ball or gear.",
            correctAnswer = "ALL_MATCHED",
            hint = "Cricket uses a bat, Football uses a goal net!",
            explanation = "Goal! You are a sports champion!",
            visualEmoji = "🏏",
            matchPairs = listOf("Cricket" to "🏏 Bat", "Football" to "⚽ Goal", "Badminton" to "🏸 Shuttle")
        ),
        LearningActivity(
            id = "gk_12", worldId = WorldId.GK, title = "Good Habits Everyday",
            topicTag = "Good Habits", type = ActivityType.MULTIPLE_CHOICE, difficulty = DifficultyLevel.BEGINNER,
            prompt = "What should we always do BEFORE eating any meal?",
            promptBn = "খাবার খাওয়ার আগে সবসময় কী করা উচিত?",
            speechText = "What should we always do before eating food?",
            options = listOf("Wash hands with soap 🧼", "Run in mud", "Forget to chew", "Watch loud TV"),
            correctAnswer = "Wash hands with soap 🧼",
            hint = "Bubbly soap chases germs away!",
            explanation = "Clean hands keep tummies happy and healthy!",
            visualEmoji = "🧼"
        ),
        LearningActivity(
            id = "gk_13", worldId = WorldId.GK, title = "Safety Awareness: Traffic Light",
            topicTag = "Safety Awareness", type = ActivityType.MULTIPLE_CHOICE, difficulty = DifficultyLevel.BEGINNER,
            prompt = "When a traffic light turns RED 🚦, what must cars do?",
            promptBn = "ট্রাফিক লাইট লাল (RED) হলে গাড়ি কী করবে?",
            speechText = "What does the red traffic light mean?",
            options = listOf("STOP", "GO FAST", "TURN LEFT ONLY", "HONK LOUDLY"),
            correctAnswer = "STOP",
            hint = "Red = Stop, Yellow = Wait, Green = Go!",
            explanation = "Red means STOP to keep everyone safe!",
            visualEmoji = "🚦"
        ),
        LearningActivity(
            id = "gk_14", worldId = WorldId.GK, title = "Guess the Country: Kangaroos",
            topicTag = "Guess the Country", type = ActivityType.MULTIPLE_CHOICE, difficulty = DifficultyLevel.EXPLORER,
            prompt = "I am surrounded by oceans and kangaroos hop across my sunny lands. Which country am I?",
            promptBn = "ক্যাঙ্গারু আর কোয়ালা কোন দেশে থাকে?",
            speechText = "Guess the country where kangaroos and koalas live.",
            options = listOf("Australia 🇦🇺", "France 🇫🇷", "Bangladesh 🇧🇩", "Norway 🇳🇴"),
            correctAnswer = "Australia 🇦🇺",
            hint = "Its biggest city has the Sydney Opera House!",
            explanation = "Australia! Known as the land down under!",
            visualEmoji = "🦘"
        ),
        LearningActivity(
            id = "gk_15", worldId = WorldId.GK, title = "Bangladesh Rivers",
            topicTag = "Bangladesh", type = ActivityType.MULTIPLE_CHOICE, difficulty = DifficultyLevel.MASTER,
            prompt = "Bangladesh is called the 'Land of ___' because hundreds of rivers flow through it!",
            promptBn = "বাংলাদেশকে কিসের দেশ বলা হয়?",
            speechText = "Bangladesh is known as the Land of what?",
            options = listOf("Rivers", "Deserts", "Glaciers", "Volcanoes"), correctAnswer = "Rivers",
            hint = "Padma, Meghna, and Jamuna are famous ones!",
            explanation = "Yes! Bangladesh is the beautiful Land of Rivers!",
            visualEmoji = "🏞️"
        ),
        LearningActivity(
            id = "gk_16", worldId = WorldId.GK, title = "National Bird of Bangladesh",
            topicTag = "Bangladesh", type = ActivityType.MULTIPLE_CHOICE, difficulty = DifficultyLevel.EXPLORER,
            prompt = "Which black-and-white singing bird is the National Bird of Bangladesh?",
            promptBn = "বাংলাদেশের জাতীয় পাখি কোনটি?",
            speechText = "Which singing bird is the national bird of Bangladesh?",
            options = listOf("Doyel (Magpie Robin)", "Penguin", "Ostrich", "Flamingo"),
            correctAnswer = "Doyel (Magpie Robin)",
            hint = "Its sweet morning song starts with D!",
            explanation = "The lovely Doyel (Magpie Robin) is Bangladesh's national bird!",
            visualEmoji = "🐦"
        ),
        LearningActivity(
            id = "gk_17", worldId = WorldId.GK, title = "Who Am I? Astronaut",
            topicTag = "Professions", type = ActivityType.MULTIPLE_CHOICE, difficulty = DifficultyLevel.EXPLORER,
            prompt = "Who am I? I wear a space helmet and travel in a rocket to study stars and planets!",
            promptBn = "রকেটে চড়ে মহাকাশে কে যায়?",
            speechText = "Who flies in a rocket to explore space?",
            options = listOf("Astronaut 🧑‍🚀", "Carpenter 🪚", "Fisherman 🎣", "Librarian 📚"),
            correctAnswer = "Astronaut 🧑‍🚀",
            hint = "Astro means star!",
            explanation = "3, 2, 1, Blast off! Astronauts explore space!",
            visualEmoji = "🧑‍🚀", isVocabulary = true
        ),
        LearningActivity(
            id = "gk_18", worldId = WorldId.GK, title = "World Ocean Giant",
            topicTag = "Continents", type = ActivityType.MULTIPLE_CHOICE, difficulty = DifficultyLevel.MASTER,
            prompt = "Which is the largest ocean on Earth?",
            promptBn = "পৃথিবীর সবচেয়ে বড় মহাসাগর কোনটি?",
            speechText = "Which is the largest ocean on planet Earth?",
            options = listOf("Pacific Ocean", "Red Sea", "Padma River", "Arctic Pond"),
            correctAnswer = "Pacific Ocean",
            hint = "Its name starts with P and means peaceful!",
            explanation = "The Pacific Ocean covers nearly one third of Earth!",
            visualEmoji = "🌊"
        )
    )

    val logicActivities: List<LearningActivity> = listOf(
        LearningActivity(
            id = "log_1", worldId = WorldId.LOGIC, title = "Kids Sudoku 4x4 — Level 1",
            topicTag = "Sudoku 4x4", type = ActivityType.SUDOKU_4X4, difficulty = DifficultyLevel.BEGINNER,
            prompt = "Fill the empty squares so every row and column has numbers 1, 2, 3, and 4 once!",
            promptBn = "প্রতিটি সারি ও কলামে ১, ২, ৩, ৪ একবার করে বসাও!",
            speechText = "Solve the 4 by 4 Kids Sudoku! Tap an empty square and pick 1, 2, 3, or 4.",
            correctAnswer = "SOLVED",
            hint = "Look at a row that already has 3 numbers — the missing one is easy to spot!",
            explanation = "Sudoku superstar! Every row and column has 1, 2, 3, 4!",
            visualEmoji = "🔢",
            sudokuGrid = listOf(
                1, 2, 3, 0,
                3, 4, 0, 2,
                2, 0, 4, 3,
                0, 3, 2, 1
            ),
            sudokuSolution = listOf(
                1, 2, 3, 4,
                3, 4, 1, 2,
                2, 1, 4, 3,
                4, 3, 2, 1
            )
        ),
        LearningActivity(
            id = "log_2", worldId = WorldId.LOGIC, title = "Kids Sudoku 4x4 — Level 2",
            topicTag = "Sudoku 4x4", type = ActivityType.SUDOKU_4X4, difficulty = DifficultyLevel.EXPLORER,
            prompt = "Level 2 Sudoku! Fill each blank box with 1–4 without repeating in any row or column.",
            promptBn = "লেভেল ২ সুডোকু সমাধান করো!",
            speechText = "Fill all empty Sudoku cells with numbers 1 to 4.",
            correctAnswer = "SOLVED",
            hint = "Check both across (row) and down (column) before placing a number!",
            explanation = "Sharp logic! Level 2 Sudoku conquered!",
            visualEmoji = "🧩",
            sudokuGrid = listOf(
                2, 0, 4, 1,
                4, 1, 0, 3,
                1, 4, 3, 0,
                0, 2, 1, 4
            ),
            sudokuSolution = listOf(
                2, 3, 4, 1,
                4, 1, 2, 3,
                1, 4, 3, 2,
                3, 2, 1, 4
            )
        ),
        LearningActivity(
            id = "log_3", worldId = WorldId.LOGIC, title = "Kids Sudoku 4x4 — Master",
            topicTag = "Sudoku 4x4", type = ActivityType.SUDOKU_4X4, difficulty = DifficultyLevel.MASTER,
            prompt = "Master Sudoku Challenge! Use careful detective work to complete the 4×4 grid.",
            promptBn = "মাস্টার ৪×৪ সুডোকু চ্যালেঞ্জ!",
            speechText = "Complete this Master 4 by 4 Sudoku puzzle!",
            correctAnswer = "SOLVED",
            hint = "Start with the rows or columns that have only 1 or 2 empty spaces!",
            explanation = "Incredible brainpower! You solved Master Sudoku!",
            visualEmoji = "🏅",
            sudokuGrid = listOf(
                0, 2, 0, 4,
                4, 3, 2, 0,
                2, 0, 4, 3,
                3, 4, 0, 2
            ),
            sudokuSolution = listOf(
                1, 2, 3, 4,
                4, 3, 2, 1,
                2, 1, 4, 3,
                3, 4, 1, 2
            )
        ),
        LearningActivity(
            id = "log_4", worldId = WorldId.LOGIC, title = "Pattern Train: Fruits",
            topicTag = "Pattern Recognition", type = ActivityType.FILL_BLANK, difficulty = DifficultyLevel.BEGINNER,
            prompt = "Complete the pattern: 🍎 🍌 🍇 🍎 🍌 ___",
            promptBn = "ছন্দ অনুযায়ী পরের ফল কোনটি?",
            speechText = "Apple, Banana, Grape, Apple, Banana, what comes next?",
            options = listOf("🍇", "🍎", "🍌", "🍉"), correctAnswer = "🍇",
            hint = "The pattern repeats every 3 fruits!",
            explanation = "Grape completes the 3-fruit pattern!",
            visualEmoji = "🚂"
        ),
        LearningActivity(
            id = "log_5", worldId = WorldId.LOGIC, title = "Growing Number Sequence",
            topicTag = "Sequence Completion", type = ActivityType.FILL_BLANK, difficulty = DifficultyLevel.BEGINNER,
            prompt = "Find the missing number: 2, 4, 6, ___, 10",
            promptBn = "২, ৪, ৬, ___, ১০ — মাঝে কী বসবে?",
            speechText = "2, 4, 6, blank, 10. What number completes the sequence?",
            options = listOf("7", "8", "9", "12"), correctAnswer = "8",
            hint = "Add 2 each jump!",
            explanation = "2, 4, 6, 8, 10! Counting by 2s!",
            visualEmoji = "🦘"
        ),
        LearningActivity(
            id = "log_6", worldId = WorldId.LOGIC, title = "Odd One Out: Seasons",
            topicTag = "Logical Reasoning", type = ActivityType.MULTIPLE_CHOICE, difficulty = DifficultyLevel.BEGINNER,
            prompt = "Which item does NOT belong in the group: 🍎 Apple, 🍌 Banana, 🚲 Bicycle, 🥭 Mango?",
            promptBn = "নিচের কোনটি অন্যগুলোর চেয়ে আলাদা?",
            speechText = "Which one does not belong: Apple, Banana, Bicycle, or Mango?",
            options = listOf("🚲 Bicycle", "🍎 Apple", "🍌 Banana", "🥭 Mango"),
            correctAnswer = "🚲 Bicycle",
            hint = "Three of them are yummy to eat!",
            explanation = "Bicycle is a vehicle, while the other three are fruits!",
            visualEmoji = "🔎"
        ),
        LearningActivity(
            id = "log_7", worldId = WorldId.LOGIC, title = "Find the Difference",
            topicTag = "Find the Difference", type = ActivityType.SPOT_DIFFERENCE, difficulty = DifficultyLevel.BEGINNER,
            prompt = "Compare Garden A and Garden B! Tap the 2 things that changed in Garden B!",
            promptBn = "দুই ছবির মধ্যে যে ২টি জিনিস বদলে গেছে তা খুঁজে বের করো!",
            speechText = "Look carefully! Find the 2 items in Garden B that are different from Garden A.",
            correctAnswer = "FOUND_ALL",
            hint = "Check the butterfly and the star!",
            explanation = "Eagle eyes! You spotted every difference!",
            visualEmoji = "🕵️",
            differenceTargets = listOf("🐛 Caterpillar (was 🦋)", "🌙 Moon (was ⭐)"),
            options = listOf("🌳 Tree", "🌸 Flower", "🐛 Caterpillar (was 🦋)", "☀️ Sun", "🌙 Moon (was ⭐)", "🐝 Bee")
        ),
        LearningActivity(
            id = "log_8", worldId = WorldId.LOGIC, title = "Shape Riddle",
            topicTag = "Shape Puzzles", type = ActivityType.MULTIPLE_CHOICE, difficulty = DifficultyLevel.BEGINNER,
            prompt = "If you glue two identical squares side-by-side, what new shape do you make?",
            promptBn = "দুটি বর্গক্ষেত্র পাশাপাশি জোড়া লাগালে কী আকার তৈরি হয়?",
            speechText = "Put two squares side by side. What longer shape do you get?",
            options = listOf("Rectangle", "Circle", "Triangle", "Oval"), correctAnswer = "Rectangle",
            hint = "It still has 4 corners, with two long sides and two short sides!",
            explanation = "Two squares side-by-side form a Rectangle!",
            visualEmoji = "🟨"
        ),
        LearningActivity(
            id = "log_9", worldId = WorldId.LOGIC, title = "Animal Weight Logic",
            topicTag = "Logical Reasoning", type = ActivityType.MULTIPLE_CHOICE, difficulty = DifficultyLevel.EXPLORER,
            prompt = "A Dog is heavier than a Cat. A Cat is heavier than a Mouse. Which one is the LIGHTEST?",
            promptBn = "কুকুর বিড়ালের চেয়ে ভারী, বিড়াল ইঁদুরের চেয়ে ভারী। সবচেয়ে হালকা কোনটি?",
            speechText = "A dog is heavier than a cat, and a cat is heavier than a mouse. Who is lightest?",
            options = listOf("Mouse 🐭", "Dog 🐶", "Cat 🐱"), correctAnswer = "Mouse 🐭",
            hint = "Smallest animal at the end of the chain!",
            explanation = "Great deduction! The tiny Mouse is the lightest!",
            visualEmoji = "🐭"
        ),
        LearningActivity(
            id = "log_10", worldId = WorldId.LOGIC, title = "Sequence Completion: Counting Back",
            topicTag = "Sequence Completion", type = ActivityType.FILL_BLANK, difficulty = DifficultyLevel.EXPLORER,
            prompt = "Rocket countdown! 10, 8, 6, ___, 2",
            promptBn = "১০, ৮, ৬, ___, ২ — মাঝের সংখ্যাটি কত?",
            speechText = "10, 8, 6, blank, 2. Which number is missing?",
            options = listOf("5", "4", "3", "1"), correctAnswer = "4",
            hint = "Subtract 2 at each step!",
            explanation = "10, 8, 6, 4, 2 — Blast off!",
            visualEmoji = "🚀"
        ),
        LearningActivity(
            id = "log_11", worldId = WorldId.LOGIC, title = "Color Code Matching",
            topicTag = "Pattern Recognition", type = ActivityType.PAIR_MATCH, difficulty = DifficultyLevel.EXPLORER,
            prompt = "Match each shape rule to the matching shape!",
            promptBn = "বৈশিষ্ট্যের সাথে সঠিক আকার মেলাও!",
            speechText = "Match each clue to the right shape.",
            correctAnswer = "ALL_MATCHED",
            hint = "0 corners = circle, 3 corners = triangle, 4 corners = square!",
            explanation = "Pure logical perfection!",
            visualEmoji = "🔷",
            matchPairs = listOf("0 corners" to "⭕", "3 corners" to "🔺", "4 corners" to "🟦")
        ),
        LearningActivity(
            id = "log_12", worldId = WorldId.LOGIC, title = "Problem Solving: Bridge Crossing",
            topicTag = "Problem Solving", type = ActivityType.MULTIPLE_CHOICE, difficulty = DifficultyLevel.EXPLORER,
            prompt = "Maya needs to carry a heavy pumpkin across a stream. A plank holds 10 kg. Maya + Pumpkin = 12 kg, Maya alone = 8 kg. How should she cross safely?",
            promptBn = "মায়া ৮ কেজি আর কুমড়ো ৪ কেজি, কিন্তু সাঁকো নেয় ১০ কেজি। কীভাবে পার হবে?",
            speechText = "Maya weighs 8 and the pumpkin weighs 4, but the bridge holds 10. How can she get both across?",
            options = listOf("Cross first, or roll pumpkin separately", "Jump together at once", "Throw it into the river", "Give up"),
            correctAnswer = "Cross first, or roll pumpkin separately",
            hint = "8 is under 10, and 4 is under 10!",
            explanation = "Smart problem-solving! Moving lighter loads separately keeps the bridge safe!",
            visualEmoji = "🌉"
        ),
        LearningActivity(
            id = "log_13", worldId = WorldId.LOGIC, title = "Memory Cards: Logic Gems",
            topicTag = "Memory & Logic", type = ActivityType.MEMORY_CARDS, difficulty = DifficultyLevel.BEGINNER,
            prompt = "Flip cards and match all 3 glowing pairs!",
            promptBn = "কার্ড উল্টিয়ে একই জোড়াগুলো মেলাও!",
            speechText = "Flip cards to find matching pairs!",
            correctAnswer = "ALL_FOUND",
            hint = "Remember where each icon hides!",
            explanation = "Super sharp memory!",
            visualEmoji = "💎",
            memoryPairs = listOf("💎" to "💎", "⭐" to "⭐", "🦋" to "🦋")
        ),
        LearningActivity(
            id = "log_14", worldId = WorldId.LOGIC, title = "Sequence Recall: Robot Steps",
            topicTag = "Sequence Completion", type = ActivityType.SEQUENCE_RECALL, difficulty = DifficultyLevel.EXPLORER,
            prompt = "Memorize the 3-step robot dance, then tap them in the same order!",
            promptBn = "রোবটের নাচের ধাপগুলো মনে রেখে পরপর ট্যাপ করো!",
            speechText = "Watch the sequence carefully and tap the icons in order.",
            correctAnswer = "RECALLED",
            hint = "Say the pictures out loud in your mind!",
            explanation = "Beep-boop! You programmed the robot dance perfectly!",
            visualEmoji = "🤖",
            sequenceItems = listOf("🤖", "⚡", "🚀")
        ),
        LearningActivity(
            id = "log_15", worldId = WorldId.LOGIC, title = "Double Pattern Puzzle",
            topicTag = "Pattern Recognition", type = ActivityType.FILL_BLANK, difficulty = DifficultyLevel.MASTER,
            prompt = "What comes next? A1, B2, C3, ___",
            promptBn = "A1, B2, C3 — এরপর কী হবে?",
            speechText = "A1, B2, C3... what comes next?",
            options = listOf("D4", "D5", "E4", "C4"), correctAnswer = "D4",
            hint = "Next letter after C is D, and next number after 3 is 4!",
            explanation = "Brilliant! D4 follows C3!",
            visualEmoji = "🔠"
        ),
        LearningActivity(
            id = "log_16", worldId = WorldId.LOGIC, title = "Direction Logic: Compass",
            topicTag = "Problem Solving", type = ActivityType.MULTIPLE_CHOICE, difficulty = DifficultyLevel.MASTER,
            prompt = "You face North. Turn right once. Now you face East. Turn right one more time—where do you face?",
            promptBn = "উত্তর দিক থেকে দুইবার ডানে ঘুরলে কোন দিকে মুখ হবে?",
            speechText = "Start facing North, turn right to East, turn right again. Which way are you facing?",
            options = listOf("South", "West", "North", "Up"), correctAnswer = "South",
            hint = "North → East → South → West!",
            explanation = "Compass master! Turning right twice turns you toward South!",
            visualEmoji = "🧭"
        ),
        LearningActivity(
            id = "log_17", worldId = WorldId.LOGIC, title = "Sort Heavy vs Light",
            topicTag = "Logical Reasoning", type = ActivityType.DRAG_SORT, difficulty = DifficultyLevel.BEGINNER,
            prompt = "Sort objects into Heavy or Light!",
            promptBn = "ভারী আর হালকা জিনিস আলাদা করো!",
            speechText = "Sort the items into Heavy and Light.",
            correctAnswer = "ALL_SORTED",
            hint = "A feather floats in air; an elephant is huge!",
            explanation = "Great reasoning!",
            visualEmoji = "⚖️",
            buckets = listOf("Heavy", "Light"),
            dragItems = listOf("Elephant 🐘" to "Heavy", "Feather 🪶" to "Light", "Bus 🚌" to "Heavy", "Bubble 🫧" to "Light")
        ),
        LearningActivity(
            id = "log_18", worldId = WorldId.LOGIC, title = "Shape Count Mystery",
            topicTag = "Shape Puzzles", type = ActivityType.MULTIPLE_CHOICE, difficulty = DifficultyLevel.MASTER,
            prompt = "A house drawing has 1 square wall + 1 triangle roof + 1 rectangle door. How many shapes in total?",
            promptBn = "একটি বাড়ির ছবিতে ১টি বর্গ, ১টি ত্রিভুজ ও ১টি আয়তক্ষেত্র থাকলে মোট কয়টি আকার?",
            speechText = "One square wall, one triangle roof, one rectangle door. How many shapes total?",
            options = listOf("2", "3", "4", "5"), correctAnswer = "3",
            hint = "1 + 1 + 1 = ?",
            explanation = "3 shapes build a cozy house!",
            visualEmoji = "🏠"
        ),
        LearningActivity(
            id = "log_19", worldId = WorldId.LOGIC, title = "True or False Logic",
            topicTag = "Logical Reasoning", type = ActivityType.MULTIPLE_CHOICE, difficulty = DifficultyLevel.EXPLORER,
            prompt = "All penguins are birds. Pingu is a penguin. So Pingu MUST be a...",
            promptBn = "সব পেঙ্গুইন পাখি। পিঙ্গু একটি পেঙ্গুইন, তাই পিঙ্গু অবশ্যই একটি...",
            speechText = "All penguins are birds. Pingu is a penguin. What must Pingu be?",
            options = listOf("Bird", "Fish", "Train", "Flower"), correctAnswer = "Bird",
            hint = "Follow the first clue!",
            explanation = "Logical deduction! Pingu is a bird!",
            visualEmoji = "🐧"
        ),
        LearningActivity(
            id = "log_20", worldId = WorldId.LOGIC, title = "Spot Difference: Space",
            topicTag = "Find the Difference", type = ActivityType.SPOT_DIFFERENCE, difficulty = DifficultyLevel.MASTER,
            prompt = "Spot the 2 changes in Space Station B!",
            promptBn = "স্পেস স্টেশন বি-তে বদলে যাওয়া ২টি জিনিস খুঁজে বের করো!",
            speechText = "Find the 2 items that changed in Space Station B!",
            correctAnswer = "FOUND_ALL",
            hint = "Look for the comet and the alien!",
            explanation = "Space detective! You found both changes!",
            visualEmoji = "🛸",
            differenceTargets = listOf("🛸 UFO (was 🚀)", "☄️ Comet (was 🪐)"),
            options = listOf("🌍 Earth", "⭐ Star", "🛸 UFO (was 🚀)", "👩‍🚀 Astronaut", "☄️ Comet (was 🪐)", "🌕 Moon")
        )
    )
}
