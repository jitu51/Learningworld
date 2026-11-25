package com.example.data.content

import com.example.data.model.ActivityType
import com.example.data.model.DifficultyLevel
import com.example.data.model.LearningActivity
import com.example.data.model.WorldId

object ContentCatalogPart1 {
    val englishActivities: List<LearningActivity> = listOf(
        LearningActivity(
            id = "eng_1", worldId = WorldId.ENGLISH, title = "Alphabet A-Z: Apple Sound",
            topicTag = "Alphabet A-Z", type = ActivityType.AUDIO_LISTEN, difficulty = DifficultyLevel.BEGINNER,
            prompt = "Listen closely! Which letter makes the /a/ sound in 'Apple'?",
            promptBn = "'Apple' শব্দের প্রথম অক্ষর কোনটি?",
            speechText = "A is for Apple! Ah, ah, Apple. Which letter starts the word Apple?",
            options = listOf("A", "B", "C", "D"), correctAnswer = "A",
            hint = "It is the very first letter of the alphabet!",
            explanation = "Yes! A makes the 'ah' sound in Apple.",
            explanationBn = "হ্যাঁ! A দিয়ে Apple হয়।",
            visualEmoji = "🍎", isVocabulary = true
        ),
        LearningActivity(
            id = "eng_2", worldId = WorldId.ENGLISH, title = "Phonics: B is for Ball",
            topicTag = "Phonics", type = ActivityType.PICTURE_MATCH, difficulty = DifficultyLevel.BEGINNER,
            prompt = "Look at the bouncy toy! Which word matches the picture?",
            promptBn = "ছবির সাথে কোন শব্দটি মেলে?",
            speechText = "Look at the picture. Which word starts with B?",
            options = listOf("Ball", "Cat", "Sun", "Tree"), correctAnswer = "Ball",
            hint = "You bounce and kick it in the park!",
            explanation = "Awesome! B-A-L-L spells Ball!",
            visualEmoji = "⚽", isVocabulary = true
        ),
        LearningActivity(
            id = "eng_3", worldId = WorldId.ENGLISH, title = "Alphabet Order",
            topicTag = "Alphabet A-Z", type = ActivityType.FILL_BLANK, difficulty = DifficultyLevel.BEGINNER,
            prompt = "Fill in the missing letter: A, B, __, D, E",
            promptBn = "খালি ঘরে কোন অক্ষর বসবে: A, B, __, D, E?",
            speechText = "A, B, blank, D, E. Which letter is missing?",
            options = listOf("C", "F", "G", "Z"), correctAnswer = "C",
            hint = "Sing the ABC song: A, B, ...!",
            explanation = "C comes right between B and D!",
            visualEmoji = "🔤", isVocabulary = true
        ),
        LearningActivity(
            id = "eng_4", worldId = WorldId.ENGLISH, title = "Wild Animal Vocabulary",
            topicTag = "Animals", type = ActivityType.MULTIPLE_CHOICE, difficulty = DifficultyLevel.BEGINNER,
            prompt = "Which animal has black and white stripes?",
            promptBn = "কোন প্রাণীর গায়ে সাদা-কালো ডোরাকাটা দাগ থাকে?",
            speechText = "Which animal has black and white stripes?",
            options = listOf("Zebra", "Frog", "Duck", "Bunny"), correctAnswer = "Zebra",
            hint = "Its name starts with the last letter Z!",
            explanation = "Zebras have cool black and white stripes!",
            visualEmoji = "🦓", isVocabulary = true
        ),
        LearningActivity(
            id = "eng_5", worldId = WorldId.ENGLISH, title = "Sort Fruits & Animals",
            topicTag = "Fruits & Animals", type = ActivityType.DRAG_SORT, difficulty = DifficultyLevel.BEGINNER,
            prompt = "Sort each item into Fruits or Animals!",
            promptBn = "ফল আর প্রাণী আলাদা ঝুড়িতে সাজাও!",
            speechText = "Tap each word and place it into Fruits or Animals.",
            correctAnswer = "ALL_SORTED",
            hint = "Mango and Banana are yummy fruits. Tiger and Cow are animals!",
            explanation = "Super sorting! You know your fruits and animals!",
            visualEmoji = "🧺", isVocabulary = true,
            buckets = listOf("Fruits", "Animals"),
            dragItems = listOf("Mango 🥭" to "Fruits", "Tiger 🐯" to "Animals", "Banana 🍌" to "Fruits", "Cow 🐄" to "Animals")
        ),
        LearningActivity(
            id = "eng_6", worldId = WorldId.ENGLISH, title = "Build the Word: CAT",
            topicTag = "Word Building", type = ActivityType.WORD_BUILD, difficulty = DifficultyLevel.BEGINNER,
            prompt = "Tap the letters in order to spell the furry pet: 🐱",
            promptBn = "অক্ষরগুলো সাজিয়ে CAT শব্দটি তৈরি করো।",
            speechText = "Spell the word Cat using the letter tiles.",
            options = listOf("T", "C", "A"), correctAnswer = "CAT",
            hint = "Start with C, then A, then T!",
            explanation = "Purr-fect! C-A-T spells CAT!",
            visualEmoji = "🐱", isVocabulary = true
        ),
        LearningActivity(
            id = "eng_7", worldId = WorldId.ENGLISH, title = "Colorful Rainbow Words",
            topicTag = "Colors", type = ActivityType.PICTURE_MATCH, difficulty = DifficultyLevel.BEGINNER,
            prompt = "What color is a ripe sunny lemon? 🍋",
            promptBn = "পাকা লেবুর রঙ কী?",
            speechText = "What color is a bright lemon?",
            options = listOf("Yellow", "Purple", "Blue", "Black"), correctAnswer = "Yellow",
            hint = "It is the same bright color as the sun!",
            explanation = "Yes! Lemons and sunshine are Yellow.",
            visualEmoji = "🍋", isVocabulary = true
        ),
        LearningActivity(
            id = "eng_8", worldId = WorldId.ENGLISH, title = "Healthy Vegetables",
            topicTag = "Vegetables", type = ActivityType.MULTIPLE_CHOICE, difficulty = DifficultyLevel.BEGINNER,
            prompt = "Which crunchy orange vegetable do rabbits love?",
            promptBn = "খরগোশ কোন কমলা রঙের সবজি পছন্দ করে?",
            speechText = "Which crunchy orange vegetable do rabbits love?",
            options = listOf("Carrot", "Potato", "Onion", "Pea"), correctAnswer = "Carrot",
            hint = "It grows underground and starts with C!",
            explanation = "Carrots help keep our eyes bright and strong!",
            visualEmoji = "🥕", isVocabulary = true
        ),
        LearningActivity(
            id = "eng_9", worldId = WorldId.ENGLISH, title = "Speak & Repeat: Sun",
            topicTag = "Pronunciation", type = ActivityType.VOICE_SPEAK, difficulty = DifficultyLevel.BEGINNER,
            prompt = "Tap Speak and say the word: 'Sunny Day'",
            promptBn = "মাইকে ট্যাপ করে বলো: Sunny Day",
            speechText = "Say Sunny Day out loud!",
            correctAnswer = "sunny",
            hint = "Say 'Sun-nee Day' clearly into the microphone, or type 'sunny'!",
            explanation = "Wonderful voice! Speaking English makes you super confident!",
            visualEmoji = "☀️", isVocabulary = true
        ),
        LearningActivity(
            id = "eng_10", worldId = WorldId.ENGLISH, title = "Everyday Polite Words",
            topicTag = "Everyday English", type = ActivityType.MULTIPLE_CHOICE, difficulty = DifficultyLevel.BEGINNER,
            prompt = "When someone gives you a gift, what polite words do you say?",
            promptBn = "কেউ উপহার দিলে ভদ্রভাবে কী বলতে হয়?",
            speechText = "When someone gives you a gift, what polite words do you say?",
            options = listOf("Thank you!", "Go away!", "Never mind", "Hurry up"), correctAnswer = "Thank you!",
            hint = "Polite words show gratitude!",
            explanation = "Saying 'Thank you!' always brings smiles.",
            visualEmoji = "🎁", isVocabulary = true
        ),
        LearningActivity(
            id = "eng_11", worldId = WorldId.ENGLISH, title = "Opposite Words Match",
            topicTag = "Opposites", type = ActivityType.PAIR_MATCH, difficulty = DifficultyLevel.EXPLORER,
            prompt = "Match each word to its opposite!",
            promptBn = "প্রতিটি শব্দের বিপরীত শব্দ মেলাও!",
            speechText = "Match the opposite words together.",
            correctAnswer = "ALL_MATCHED",
            hint = "Hot is opposite to Cold. Big is opposite to Small!",
            explanation = "Fantastic! You mastered opposite words!",
            visualEmoji = "⚖️", isVocabulary = true,
            matchPairs = listOf("Hot 🔥" to "Cold ❄️", "Big 🐘" to "Small 🐭", "Day ☀️" to "Night 🌙")
        ),
        LearningActivity(
            id = "eng_12", worldId = WorldId.ENGLISH, title = "One or Many: Singular & Plural",
            topicTag = "Singular & Plural", type = ActivityType.MULTIPLE_CHOICE, difficulty = DifficultyLevel.EXPLORER,
            prompt = "One book, two ____. Pick the correct plural word!",
            promptBn = "একটি হলে book, দুটি হলে কী হবে?",
            speechText = "One book, two what? Pick the plural form.",
            options = listOf("Books", "Bookes", "Bookies", "Book"), correctAnswer = "Books",
            hint = "Add an 's' at the end when there is more than one!",
            explanation = "Right! One book, two books!",
            visualEmoji = "📚", isVocabulary = true
        ),
        LearningActivity(
            id = "eng_13", worldId = WorldId.ENGLISH, title = "Spell Missing Letter: _ISH",
            topicTag = "Spelling", type = ActivityType.FILL_BLANK, difficulty = DifficultyLevel.EXPLORER,
            prompt = "Complete the swimming animal word: __ I S H",
            promptBn = "শূন্যস্থানে কোন অক্ষর বসবে: __ISH?",
            speechText = "Which letter completes the word for fish?",
            options = listOf("F", "P", "M", "R"), correctAnswer = "F",
            hint = "It makes the /fff/ sound!",
            explanation = "Splendid! F-I-S-H spells Fish!",
            visualEmoji = "🐟", isVocabulary = true
        ),
        LearningActivity(
            id = "eng_14", worldId = WorldId.ENGLISH, title = "Simple Sentences",
            topicTag = "Sentences", type = ActivityType.FILL_BLANK, difficulty = DifficultyLevel.EXPLORER,
            prompt = "Complete the sentence: 'The bird ___ in the sky.'",
            promptBn = "বাক্যটি সম্পূর্ণ করো: 'The bird ___ in the sky.'",
            speechText = "The bird blank in the sky. Pick the action word.",
            options = listOf("flies", "swims", "reads", "cooks"), correctAnswer = "flies",
            hint = "Birds use wings in the sky!",
            explanation = "Great grammar! The bird flies in the sky.",
            visualEmoji = "🐦", isVocabulary = true
        ),
        LearningActivity(
            id = "eng_15", worldId = WorldId.ENGLISH, title = "Build the Word: STAR",
            topicTag = "Word Building", type = ActivityType.WORD_BUILD, difficulty = DifficultyLevel.EXPLORER,
            prompt = "Arrange the letters to spell the shining night light: ⭐",
            promptBn = "অক্ষর সাজিয়ে STAR শব্দটি তৈরি করো।",
            speechText = "Spell the word Star with the letter tiles.",
            options = listOf("R", "S", "A", "T"), correctAnswer = "STAR",
            hint = "S - T - A - R!",
            explanation = "You are a shining STAR!",
            visualEmoji = "⭐", isVocabulary = true
        ),
        LearningActivity(
            id = "eng_16", worldId = WorldId.ENGLISH, title = "Action Verbs (Grammar)",
            topicTag = "Basic Grammar", type = ActivityType.DRAG_SORT, difficulty = DifficultyLevel.EXPLORER,
            prompt = "Sort Naming Words (Nouns) and Action Words (Verbs)!",
            promptBn = "নাম আর কাজের শব্দ আলাদা করো!",
            speechText = "Sort nouns and verbs into the right boxes.",
            correctAnswer = "ALL_SORTED",
            hint = "Jump and Run are actions you can do!",
            explanation = "Grammar superstar! Nouns name things, Verbs show action!",
            visualEmoji = "🏃", isVocabulary = true,
            buckets = listOf("Noun (Thing)", "Verb (Action)"),
            dragItems = listOf("Dog 🐶" to "Noun (Thing)", "Jump 🦘" to "Verb (Action)", "Ball 🏀" to "Noun (Thing)", "Run 🏃" to "Verb (Action)")
        ),
        LearningActivity(
            id = "eng_17", worldId = WorldId.ENGLISH, title = "Reading Comprehension: Mia's Kite",
            topicTag = "Comprehension", type = ActivityType.MULTIPLE_CHOICE, difficulty = DifficultyLevel.MASTER,
            prompt = "Read: 'Mia took her red kite to the windy hill. Up it flew!' Where did Mia fly her kite?",
            promptBn = "গল্পটি পড়ে উত্তর দাও: মিয়া কোথায় ঘুড়ি উড়িয়েছিল?",
            speechText = "Mia took her red kite to the windy hill. Up it flew! Where did Mia fly her kite?",
            options = listOf("On a windy hill", "Under the bed", "Inside a library", "In a bathtub"), correctAnswer = "On a windy hill",
            hint = "Look at the first sentence in the story!",
            explanation = "Sharp reading! Mia flew her red kite on the windy hill.",
            visualEmoji = "🪁", isVocabulary = true
        ),
        LearningActivity(
            id = "eng_18", worldId = WorldId.ENGLISH, title = "Vocabulary Pairs: Nature",
            topicTag = "Vocabulary", type = ActivityType.PAIR_MATCH, difficulty = DifficultyLevel.MASTER,
            prompt = "Match each nature word to its matching picture!",
            promptBn = "শব্দের সাথে ছবির জুটি মেলাও!",
            speechText = "Match each nature word with its emoji.",
            correctAnswer = "ALL_MATCHED",
            hint = "Cloud floats in the sky, Flower blooms in a garden!",
            explanation = "Nature vocabulary mastered!",
            visualEmoji = "🌸", isVocabulary = true,
            matchPairs = listOf("Cloud" to "☁️", "Flower" to "🌺", "Mountain" to "⛰️")
        ),
        LearningActivity(
            id = "eng_19", worldId = WorldId.ENGLISH, title = "Speak & Repeat: Happy",
            topicTag = "Speak & Repeat", type = ActivityType.VOICE_SPEAK, difficulty = DifficultyLevel.EXPLORER,
            prompt = "Tap Speak and say: 'I am happy to learn'",
            promptBn = "বলো: I am happy to learn",
            speechText = "Say I am happy to learn!",
            correctAnswer = "happy",
            hint = "Say 'happy' clearly or type 'happy'!",
            explanation = "Hearing you speak brings so much joy!",
            visualEmoji = "😊", isVocabulary = true
        ),
        LearningActivity(
            id = "eng_20", worldId = WorldId.ENGLISH, title = "Reading Comprehension: Clever Fox",
            topicTag = "Comprehension", type = ActivityType.MULTIPLE_CHOICE, difficulty = DifficultyLevel.MASTER,
            prompt = "Read: 'Sam the fox shared his berries with a tiny bird.' What kind of friend is Sam?",
            promptBn = "শেয়াল স্যাম ছোট্ট পাখির সাথে ফল ভাগ করে খেল। স্যাম কেমন বন্ধু?",
            speechText = "Sam the fox shared his berries with a tiny bird. What kind of friend is Sam?",
            options = listOf("Kind and generous", "Sleepy and grumpy", "Afraid of berries", "Loud and messy"), correctAnswer = "Kind and generous",
            hint = "Sharing with friends shows a warm heart!",
            explanation = "Exactly! Sharing makes Sam a kind and generous friend.",
            visualEmoji = "🦊", isVocabulary = true
        )
    )

    val mathActivities: List<LearningActivity> = listOf(
        LearningActivity(
            id = "math_1", worldId = WorldId.MATH, title = "Count the Apples",
            topicTag = "Counting 1-100", type = ActivityType.PICTURE_MATCH, difficulty = DifficultyLevel.BEGINNER,
            prompt = "Count the yummy fruits: 🍎🍎🍎🍎🍎",
            promptBn = "এখানে কয়টি আপেল আছে গুনো?",
            speechText = "Count the red apples: one, two, three, four, five!",
            options = listOf("4", "5", "6", "3"), correctAnswer = "5",
            hint = "Touch each apple one by one while counting!",
            explanation = "1, 2, 3, 4, 5 apples! Great counting!",
            visualEmoji = "🍎"
        ),
        LearningActivity(
            id = "math_2", worldId = WorldId.MATH, title = "Count the Bananas",
            topicTag = "Counting 1-100", type = ActivityType.MULTIPLE_CHOICE, difficulty = DifficultyLevel.BEGINNER,
            prompt = "How many bananas are here? 🍌🍌🍌🍌🍌🍌🍌",
            promptBn = "এখানে কয়টি কলা আছে?",
            speechText = "Count how many bananas are shown.",
            options = listOf("6", "7", "8", "5"), correctAnswer = "7",
            hint = "Count carefully past 5!",
            explanation = "Yes! There are 7 sunny yellow bananas!",
            visualEmoji = "🍌"
        ),
        LearningActivity(
            id = "math_3", worldId = WorldId.MATH, title = "Feed the Hungry Monster: 3 + 2",
            topicTag = "Addition", type = ActivityType.MULTIPLE_CHOICE, difficulty = DifficultyLevel.BEGINNER,
            prompt = "Munch Monster wants 3 + 2 cookies! Pick the right plate!",
            promptBn = "দানব বন্ধুর ৩ + ২ = কতটি কুকি লাগবে?",
            speechText = "Feed the friendly monster! What is 3 plus 2?",
            options = listOf("4", "5", "6", "7"), correctAnswer = "5",
            hint = "Start at 3 and count 2 more: 4, 5!",
            explanation = "Nom nom! 3 + 2 = 5! The monster is happy!",
            visualEmoji = "👾"
        ),
        LearningActivity(
            id = "math_4", worldId = WorldId.MATH, title = "Catch the Number After 19",
            topicTag = "Counting 1-100", type = ActivityType.MULTIPLE_CHOICE, difficulty = DifficultyLevel.BEGINNER,
            prompt = "Catch the number that comes right after 19!",
            promptBn = "১৯-এর ঠিক পরের সংখ্যা কোনটি?",
            speechText = "Which number comes right after 19?",
            options = listOf("18", "20", "21", "10"), correctAnswer = "20",
            hint = "17, 18, 19, ...!",
            explanation = "Bingo! 20 comes right after 19!",
            visualEmoji = "🥅"
        ),
        LearningActivity(
            id = "math_5", worldId = WorldId.MATH, title = "Simple Addition: 4 + 4",
            topicTag = "Addition", type = ActivityType.FILL_BLANK, difficulty = DifficultyLevel.BEGINNER,
            prompt = "Double trouble! 4 + 4 = ___",
            promptBn = "৪ + ৪ = কত?",
            speechText = "What is 4 plus 4?",
            options = listOf("6", "8", "9", "10"), correctAnswer = "8",
            hint = "Count 4 fingers on one hand and 4 on the other!",
            explanation = "4 + 4 = 8! Like an octopus with 8 arms!",
            visualEmoji = "🐙"
        ),
        LearningActivity(
            id = "math_6", worldId = WorldId.MATH, title = "Sweet Subtraction: 6 - 2",
            topicTag = "Subtraction", type = ActivityType.MULTIPLE_CHOICE, difficulty = DifficultyLevel.BEGINNER,
            prompt = "You had 6 strawberries 🍓🍓🍓🍓🍓🍓 and ate 2. How many are left?",
            promptBn = "৬টি স্ট্রবেরি থেকে ২টি খেয়ে ফেললে কয়টি থাকে?",
            speechText = "You had 6 strawberries and ate 2. How many are left?",
            options = listOf("3", "4", "5", "8"), correctAnswer = "4",
            hint = "Count backwards from 6 by 2 steps: 5, 4!",
            explanation = "6 - 2 = 4 strawberries left!",
            visualEmoji = "🍓"
        ),
        LearningActivity(
            id = "math_7", worldId = WorldId.MATH, title = "Compare Numbers: Bigger One",
            topicTag = "Number Comparison", type = ActivityType.MULTIPLE_CHOICE, difficulty = DifficultyLevel.BEGINNER,
            prompt = "Alligator eats the BIGGER number! Which is bigger: 14 or 9?",
            promptBn = "১৪ আর ৯-এর মধ্যে কোন সংখ্যাটি বড়?",
            speechText = "Which number is bigger, 14 or 9?",
            options = listOf("14", "9"), correctAnswer = "14",
            hint = "Numbers with two digits (tens) are bigger than single digits!",
            explanation = "Chomp! 14 is bigger than 9!",
            visualEmoji = "🐊"
        ),
        LearningActivity(
            id = "math_8", worldId = WorldId.MATH, title = "Shape Detective: 3 Sides",
            topicTag = "Shapes", type = ActivityType.PICTURE_MATCH, difficulty = DifficultyLevel.BEGINNER,
            prompt = "Which shape has 3 straight sides and 3 corners?",
            promptBn = "কোন আকারটির ৩টি বাহু ও ৩টি কোণ আছে?",
            speechText = "Which shape has 3 straight sides?",
            options = listOf("Triangle 🔺", "Circle 🔵", "Square 🟦", "Star ⭐"), correctAnswer = "Triangle 🔺",
            hint = "Tri means three, like a tricycle with 3 wheels!",
            explanation = "Triangle has 3 sides and 3 corners!",
            visualEmoji = "🔺"
        ),
        LearningActivity(
            id = "math_9", worldId = WorldId.MATH, title = "Sort Even & Odd Numbers",
            topicTag = "Number Sense", type = ActivityType.DRAG_SORT, difficulty = DifficultyLevel.EXPLORER,
            prompt = "Sort the numbers into Even (pairable) and Odd!",
            promptBn = "জোড় ও বিজোড় সংখ্যা আলাদা করো!",
            speechText = "Sort numbers into Even and Odd buckets.",
            correctAnswer = "ALL_SORTED",
            hint = "Even numbers end in 0, 2, 4, 6, 8!",
            explanation = "Awesome number sorting!",
            visualEmoji = "🧮",
            buckets = listOf("Even", "Odd"),
            dragItems = listOf("2" to "Even", "3" to "Odd", "6" to "Even", "7" to "Odd")
        ),
        LearningActivity(
            id = "math_10", worldId = WorldId.MATH, title = "Feed Monster: Mystery Equation",
            topicTag = "Mystery Equation", type = ActivityType.FILL_BLANK, difficulty = DifficultyLevel.EXPLORER,
            prompt = "Solve the mystery: 5 + ___ = 9",
            promptBn = "৫-এর সাথে কত যোগ করলে ৯ হয়?",
            speechText = "5 plus what mystery number equals 9?",
            options = listOf("3", "4", "5", "6"), correctAnswer = "4",
            hint = "Count up from 5 until you reach 9: 6, 7, 8, 9!",
            explanation = "Mystery solved! 5 + 4 = 9!",
            visualEmoji = "🔍"
        ),
        LearningActivity(
            id = "math_11", worldId = WorldId.MATH, title = "Counting by 10s",
            topicTag = "Counting 1-100", type = ActivityType.FILL_BLANK, difficulty = DifficultyLevel.BEGINNER,
            prompt = "Jump by 10s: 10, 20, 30, ___, 50",
            promptBn = "১০, ২০, ৩০, ___, ৫০ — মাঝের সংখ্যাটি কত?",
            speechText = "Skip count by tens: 10, 20, 30, blank, 50.",
            options = listOf("35", "40", "45", "60"), correctAnswer = "40",
            hint = "After 3 tens comes 4 tens!",
            explanation = "Yes! 40 comes between 30 and 50!",
            visualEmoji = "🐸"
        ),
        LearningActivity(
            id = "math_12", worldId = WorldId.MATH, title = "Count the Oranges & Grapes",
            topicTag = "Addition", type = ActivityType.MULTIPLE_CHOICE, difficulty = DifficultyLevel.BEGINNER,
            prompt = "Count all fruits together: 🍊🍊🍊🍊 + 🍇🍇🍇 = ?",
            promptBn = "৪টি কমলা আর ৩টি আঙুর মোট কয়টি ফল?",
            speechText = "4 oranges plus 3 grapes equals how many fruits?",
            options = listOf("6", "7", "8", "9"), correctAnswer = "7",
            hint = "Count 4, then 5, 6, 7!",
            explanation = "4 + 3 = 7 delicious fruits!",
            visualEmoji = "🍊"
        ),
        LearningActivity(
            id = "math_13", worldId = WorldId.MATH, title = "Subtracting Tens: 20 - 5",
            topicTag = "Subtraction", type = ActivityType.MULTIPLE_CHOICE, difficulty = DifficultyLevel.EXPLORER,
            prompt = "A basket had 20 cherries. Birds ate 5. How many left?",
            promptBn = "২০টি চেরি থেকে ৫টি কমলে কয়টি থাকে?",
            speechText = "20 minus 5 equals what?",
            options = listOf("14", "15", "16", "10"), correctAnswer = "15",
            hint = "Count back 5 from 20!",
            explanation = "20 - 5 = 15!",
            visualEmoji = "🍒"
        ),
        LearningActivity(
            id = "math_14", worldId = WorldId.MATH, title = "Compare Big Numbers",
            topicTag = "Number Comparison", type = ActivityType.MULTIPLE_CHOICE, difficulty = DifficultyLevel.EXPLORER,
            prompt = "Which symbol fits between 42 ___ 58?",
            promptBn = "৪২ আর ৫৮-এর মাঝে কোন চিহ্ন বসবে?",
            speechText = "Is 42 less than, greater than, or equal to 58?",
            options = listOf("< (Less than)", "> (Greater than)", "= (Equal)"), correctAnswer = "< (Less than)",
            hint = "42 is smaller than 58, so the open mouth points toward 58!",
            explanation = "42 < 58! 58 is larger!",
            visualEmoji = "⚖️"
        ),
        LearningActivity(
            id = "math_15", worldId = WorldId.MATH, title = "Shape Patterns",
            topicTag = "Shapes & Patterns", type = ActivityType.FILL_BLANK, difficulty = DifficultyLevel.EXPLORER,
            prompt = "What comes next in the pattern? 🔵 🔺 🔵 🔺 🔵 ___",
            promptBn = "ছন্দের পরের আকারটি কী হবে?",
            speechText = "Circle, Triangle, Circle, Triangle, Circle... what comes next?",
            options = listOf("🔺", "🔵", "🟩", "⭐"), correctAnswer = "🔺",
            hint = "They take turns one by one!",
            explanation = "Pattern power! Triangle comes next!",
            visualEmoji = "🔷"
        ),
        LearningActivity(
            id = "math_16", worldId = WorldId.MATH, title = "Match Shapes to Sides",
            topicTag = "Shapes", type = ActivityType.PAIR_MATCH, difficulty = DifficultyLevel.EXPLORER,
            prompt = "Match each shape to how many sides it has!",
            promptBn = "প্রতিটি আকারের বাহুর সংখ্যা মেলাও!",
            speechText = "Match each shape to its number of sides.",
            correctAnswer = "ALL_MATCHED",
            hint = "Circle is round with 0 straight sides!",
            explanation = "Shape master!",
            visualEmoji = "📐",
            matchPairs = listOf("Circle ⭕" to "0 straight sides", "Triangle 🔺" to "3 sides", "Square 🟦" to "4 equal sides")
        ),
        LearningActivity(
            id = "math_17", worldId = WorldId.MATH, title = "Basic Multiplication: 2 × 4",
            topicTag = "Multiplication", type = ActivityType.MULTIPLE_CHOICE, difficulty = DifficultyLevel.EXPLORER,
            prompt = "There are 2 bowls with 4 berries in each bowl. How many berries in total? (2 × 4)",
            promptBn = "২টি বাটিতে ৪টি করে ফল থাকলে মোট কয়টি ফল?",
            speechText = "2 groups of 4 berries. What is 2 times 4?",
            options = listOf("6", "8", "10", "12"), correctAnswer = "8",
            hint = "4 + 4 equals...",
            explanation = "2 × 4 = 8 berries!",
            visualEmoji = "🥣"
        ),
        LearningActivity(
            id = "math_18", worldId = WorldId.MATH, title = "Basic Multiplication: 3 × 3",
            topicTag = "Multiplication", type = ActivityType.FILL_BLANK, difficulty = DifficultyLevel.EXPLORER,
            prompt = "3 tricycles each have 3 wheels. 3 × 3 = ___",
            promptBn = "৩টি ট্রাইসাইকেলে ৩টি করে চাকা থাকলে মোট চাকা কতটি?",
            speechText = "What is 3 times 3?",
            options = listOf("6", "9", "12", "15"), correctAnswer = "9",
            hint = "3 + 3 + 3 = ?",
            explanation = "3 × 3 = 9 wheels!",
            visualEmoji = "🛞"
        ),
        LearningActivity(
            id = "math_19", worldId = WorldId.MATH, title = "Simple Division: Sharing Cookies",
            topicTag = "Division", type = ActivityType.MULTIPLE_CHOICE, difficulty = DifficultyLevel.EXPLORER,
            prompt = "Share 10 cookies equally between 2 friends! (10 ÷ 2)",
            promptBn = "১০টি কুকি ২ জন বন্ধুকে সমান ভাগ করে দিলে প্রত্যেকে কয়টি পাবে?",
            speechText = "Share 10 cookies equally between 2 friends. How many does each friend get?",
            options = listOf("4", "5", "6", "8"), correctAnswer = "5",
            hint = "5 + 5 makes 10!",
            explanation = "10 ÷ 2 = 5 cookies each! Fair sharing!",
            visualEmoji = "🍪"
        ),
        LearningActivity(
            id = "math_20", worldId = WorldId.MATH, title = "Simple Division: 12 ÷ 3",
            topicTag = "Division", type = ActivityType.MULTIPLE_CHOICE, difficulty = DifficultyLevel.MASTER,
            prompt = "Put 12 pencils equally into 3 cups! How many in each cup?",
            promptBn = "১২টি পেন্সিল ৩টি কাপে সমান রাখলে প্রতিটি কাপে কয়টি থাকবে?",
            speechText = "12 divided by 3 equals how many?",
            options = listOf("3", "4", "5", "6"), correctAnswer = "4",
            hint = "3 × 4 = 12!",
            explanation = "12 ÷ 3 = 4 pencils in each cup!",
            visualEmoji = "✏️"
        ),
        LearningActivity(
            id = "math_21", worldId = WorldId.MATH, title = "Money Counting: Coins",
            topicTag = "Money Counting", type = ActivityType.MULTIPLE_CHOICE, difficulty = DifficultyLevel.EXPLORER,
            prompt = "You have a 5-coin and a 2-coin. How much money do you have?",
            promptBn = "৫ টাকা আর ২ টাকা মিলে মোট কত টাকা হয়?",
            speechText = "If you have 5 coins and 2 more coins, how much money do you have?",
            options = listOf("6", "7", "8", "10"), correctAnswer = "7",
            hint = "Add 5 + 2!",
            explanation = "5 + 2 = 7! Smart shopper!",
            visualEmoji = "🪙"
        ),
        LearningActivity(
            id = "math_22", worldId = WorldId.MATH, title = "Money Counting: Toy Store",
            topicTag = "Money Counting", type = ActivityType.MULTIPLE_CHOICE, difficulty = DifficultyLevel.MASTER,
            prompt = "A sticker costs 10 coins and a balloon costs 5 coins. Total cost?",
            promptBn = "স্টিকার ১০ টাকা আর বেলুন ৫ টাকা হলে মোট দাম কত?",
            speechText = "A sticker costs 10 and a balloon costs 5. What is the total?",
            options = listOf("12", "15", "20", "5"), correctAnswer = "15",
            hint = "Add 10 + 5!",
            explanation = "10 + 5 = 15 coins!",
            visualEmoji = "🎈"
        ),
        LearningActivity(
            id = "math_23", worldId = WorldId.MATH, title = "Telling Time: O'Clock",
            topicTag = "Time & Calendar", type = ActivityType.MULTIPLE_CHOICE, difficulty = DifficultyLevel.EXPLORER,
            prompt = "When the short hand points to 3 and the long hand points to 12, what time is it?",
            promptBn = "ঘড়ির ছোট কাঁটা ৩-এ আর বড় কাঁটা ১২-তে থাকলে কয়টা বাজে?",
            speechText = "Short hand on 3, long hand on 12. What time is it?",
            options = listOf("3:00", "12:00", "6:00", "9:00"), correctAnswer = "3:00",
            hint = "Look at where the short hour hand points!",
            explanation = "It is 3 o'clock (3:00)!",
            visualEmoji = "🕒"
        ),
        LearningActivity(
            id = "math_24", worldId = WorldId.MATH, title = "Calendar Fun: Days in a Week",
            topicTag = "Time & Calendar", type = ActivityType.MULTIPLE_CHOICE, difficulty = DifficultyLevel.BEGINNER,
            prompt = "How many days are there in one full week?",
            promptBn = "এক সপ্তাহে কয়টি দিন থাকে?",
            speechText = "How many days are in a week?",
            options = listOf("5", "6", "7", "10"), correctAnswer = "7",
            hint = "Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday!",
            explanation = "Yes! There are 7 wonderful days in a week!",
            visualEmoji = "📅"
        ),
        LearningActivity(
            id = "math_25", worldId = WorldId.MATH, title = "Calendar Months",
            topicTag = "Time & Calendar", type = ActivityType.MULTIPLE_CHOICE, difficulty = DifficultyLevel.EXPLORER,
            prompt = "Which month comes right after January?",
            promptBn = "জানুয়ারি মাসের ঠিক পরের মাস কোনটি?",
            speechText = "Which month comes right after January?",
            options = listOf("March", "February", "December", "April"), correctAnswer = "February",
            hint = "It is the second month of the year!",
            explanation = "January, then February!",
            visualEmoji = "🗓️"
        ),
        LearningActivity(
            id = "math_26", worldId = WorldId.MATH, title = "Catch the Number: 99 + 1",
            topicTag = "Counting 1-100", type = ActivityType.MULTIPLE_CHOICE, difficulty = DifficultyLevel.EXPLORER,
            prompt = "One more step after 99 reaches...",
            promptBn = "৯৯-এর সাথে ১ যোগ করলে কত হয়?",
            speechText = "What is 99 plus 1?",
            options = listOf("90", "98", "100", "101"), correctAnswer = "100",
            hint = "It is the big 3-digit celebration number!",
            explanation = "Hooray! 99 + 1 = 100!",
            visualEmoji = "💯"
        ),
        LearningActivity(
            id = "math_27", worldId = WorldId.MATH, title = "Mystery Equation: 15 - ___ = 8",
            topicTag = "Mystery Equation", type = ActivityType.FILL_BLANK, difficulty = DifficultyLevel.MASTER,
            prompt = "Solve the mystery: 15 - ___ = 8",
            promptBn = "১৫ থেকে কত বাদ দিলে ৮ থাকে?",
            speechText = "15 minus what mystery number equals 8?",
            options = listOf("5", "6", "7", "8"), correctAnswer = "7",
            hint = "8 + 7 = 15!",
            explanation = "Awesome! 15 - 7 = 8!",
            visualEmoji = "🧩"
        ),
        LearningActivity(
            id = "math_28", worldId = WorldId.MATH, title = "Number Puzzle: Sum to 10",
            topicTag = "Number Puzzles", type = ActivityType.PAIR_MATCH, difficulty = DifficultyLevel.EXPLORER,
            prompt = "Match pairs that add up to 10!",
            promptBn = "যে দুটি সংখ্যা যোগ করলে ১০ হয় সেগুলো মেলাও!",
            speechText = "Match number friends that make 10.",
            correctAnswer = "ALL_MATCHED",
            hint = "1+9=10, 2+8=10, 3+7=10!",
            explanation = "Ten-tastic math!",
            visualEmoji = "🔟",
            matchPairs = listOf("1 +" to "9", "2 +" to "8", "3 +" to "7")
        ),
        LearningActivity(
            id = "math_29", worldId = WorldId.MATH, title = "Feed the Monster: 5 × 2",
            topicTag = "Multiplication", type = ActivityType.MULTIPLE_CHOICE, difficulty = DifficultyLevel.MASTER,
            prompt = "Monster wants 5 packs of 2 stars! How many stars total? (5 × 2)",
            promptBn = "৫টি প্যাকেটে ২টি করে তারা থাকলে মোট তারা কতটি?",
            speechText = "What is 5 times 2?",
            options = listOf("7", "8", "10", "12"), correctAnswer = "10",
            hint = "Count by 2s five times: 2, 4, 6, 8, 10!",
            explanation = "5 × 2 = 10 shiny stars!",
            visualEmoji = "🌟"
        ),
        LearningActivity(
            id = "math_30", worldId = WorldId.MATH, title = "Number Puzzle: 25 + 15",
            topicTag = "Number Puzzles", type = ActivityType.MULTIPLE_CHOICE, difficulty = DifficultyLevel.MASTER,
            prompt = "Big challenge! What is 25 + 15?",
            promptBn = "২৫ + ১৫ সমান কত?",
            speechText = "What is 25 plus 15?",
            options = listOf("30", "35", "40", "45"), correctAnswer = "40",
            hint = "25 + 5 = 30, then add 10 more!",
            explanation = "Genius! 25 + 15 = 40!",
            visualEmoji = "🏆"
        )
    )
}
