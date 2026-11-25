package com.example.data.content

import com.example.data.model.ActivityType
import com.example.data.model.BadgeDef
import com.example.data.model.DifficultyLevel
import com.example.data.model.LearningActivity
import com.example.data.model.UnlockableCharacter
import com.example.data.model.WorldId

object ContentCatalogPart4 {
    val artActivities: List<LearningActivity> = listOf(
        LearningActivity(
            id = "art_1", worldId = WorldId.ART, title = "Primary Colors",
            topicTag = "Identify Colors", type = ActivityType.MULTIPLE_CHOICE, difficulty = DifficultyLevel.BEGINNER,
            prompt = "Which three are the magic Primary Colors used to mix other colors?",
            promptBn = "মূল তিনটি রঙ কী কী?",
            speechText = "Which three colors are the primary colors?",
            options = listOf("Red, Blue, Yellow", "Black, Gray, Brown", "Pink, White, Silver", "Green, Purple, Brown"),
            correctAnswer = "Red, Blue, Yellow",
            hint = "Think of fire trucks, ocean, and sunshine!",
            explanation = "Red, Blue, and Yellow can mix together to make a whole rainbow!",
            visualEmoji = "🎨", isVocabulary = true
        ),
        LearningActivity(
            id = "art_2", worldId = WorldId.ART, title = "Mix Red + Yellow",
            topicTag = "Color Mixing", type = ActivityType.MULTIPLE_CHOICE, difficulty = DifficultyLevel.BEGINNER,
            prompt = "Magic paint! When you mix Red ❤️ and Yellow 💛, what new color do you get?",
            promptBn = "লাল আর হলুদ রঙ মেশালে কোন রঙ তৈরি হয়?",
            speechText = "What color do you get when you mix Red and Yellow paint?",
            options = listOf("Orange 🧡", "Green 💚", "Purple 💜", "Black 🖤"),
            correctAnswer = "Orange 🧡",
            hint = "It has the same name as a juicy citrus fruit!",
            explanation = "Red + Yellow = Bright Orange!",
            visualEmoji = "🧡", isVocabulary = true
        ),
        LearningActivity(
            id = "art_3", worldId = WorldId.ART, title = "Mix Blue + Yellow",
            topicTag = "Color Mixing", type = ActivityType.MULTIPLE_CHOICE, difficulty = DifficultyLevel.BEGINNER,
            prompt = "When you mix Blue 💙 and Yellow 💛 paint, what leaf color appears?",
            promptBn = "নীল আর হলুদ মেশালে কী রঙ হয়?",
            speechText = "What color do Blue and Yellow paint make together?",
            options = listOf("Green 💚", "Red ❤️", "Pink 🩷", "Brown 🤎"),
            correctAnswer = "Green 💚",
            hint = "Think of fresh grass and tree leaves!",
            explanation = "Blue + Yellow = Grassy Green!",
            visualEmoji = "💚", isVocabulary = true
        ),
        LearningActivity(
            id = "art_4", worldId = WorldId.ART, title = "Mix Red + Blue",
            topicTag = "Color Mixing", type = ActivityType.MULTIPLE_CHOICE, difficulty = DifficultyLevel.EXPLORER,
            prompt = "Mix Red ❤️ and Blue 💙 paint! Which royal grape color pops out?",
            promptBn = "লাল আর নীল মেশালে কোন রঙ হয়?",
            speechText = "What color do Red and Blue paint make together?",
            options = listOf("Purple 💜", "Yellow 💛", "White 🤍", "Orange 🧡"),
            correctAnswer = "Purple 💜",
            hint = "Plums and lavender flowers are this color!",
            explanation = "Red + Blue = Royal Purple!",
            visualEmoji = "💜", isVocabulary = true
        ),
        LearningActivity(
            id = "art_5", worldId = WorldId.ART, title = "Shape Building: Snowman",
            topicTag = "Shape Building", type = ActivityType.MULTIPLE_CHOICE, difficulty = DifficultyLevel.BEGINNER,
            prompt = "To draw a cute snowman, which shape do you stack 3 times?",
            promptBn = "স্নোম্যান আঁকতে কোন আকার পরপর ৩টি বসাতে হয়?",
            speechText = "Which shape do you stack 3 times to draw a snowman?",
            options = listOf("Circles ⚪", "Triangles 🔺", "Stars ⭐", "Diamonds ♦️"),
            correctAnswer = "Circles ⚪",
            hint = "Round like snowballs!",
            explanation = "Three stacked circles make a cheerful snowman!",
            visualEmoji = "⛄"
        ),
        LearningActivity(
            id = "art_6", worldId = WorldId.ART, title = "Complete the Picture: Butterfly",
            topicTag = "Complete the Picture", type = ActivityType.MULTIPLE_CHOICE, difficulty = DifficultyLevel.EXPLORER,
            prompt = "A butterfly's left wing has 2 blue dots and 1 yellow stripe. What should go on the right wing to match?",
            promptBn = "প্রজাপতির বাম ডানায় ২টি নীল ফোঁটা আর ১টি হলুদ দাগ থাকলে ডান ডানায় কী থাকবে?",
            speechText = "A butterfly's wings match like a mirror! What goes on the right wing?",
            options = listOf("2 blue dots and 1 yellow stripe", "5 green squares", "No colors at all", "Only black lines"),
            correctAnswer = "2 blue dots and 1 yellow stripe",
            hint = "Butterfly wings have symmetry—both sides match!",
            explanation = "Symmetry artist! Both butterfly wings match like a mirror!",
            visualEmoji = "🦋"
        ),
        LearningActivity(
            id = "art_7", worldId = WorldId.ART, title = "Warm vs Cool Colors",
            topicTag = "Identify Colors", type = ActivityType.DRAG_SORT, difficulty = DifficultyLevel.EXPLORER,
            prompt = "Sort Warm colors (like sunshine) and Cool colors (like ocean)!",
            promptBn = "উষ্ণ রঙ আর শীতল রঙ আলাদা করো!",
            speechText = "Sort warm colors and cool colors.",
            correctAnswer = "ALL_SORTED",
            hint = "Red and Orange feel warm; Blue and Green feel cool!",
            explanation = "Color theory pro!",
            visualEmoji = "🌈",
            buckets = listOf("Warm Colors 🔥", "Cool Colors ❄️"),
            dragItems = listOf("Red ❤️" to "Warm Colors 🔥", "Blue 💙" to "Cool Colors ❄️", "Orange 🧡" to "Warm Colors 🔥", "Teal 🩵" to "Cool Colors ❄️")
        ),
        LearningActivity(
            id = "art_8", worldId = WorldId.ART, title = "Art Tools Match",
            topicTag = "Creative Tools", type = ActivityType.PAIR_MATCH, difficulty = DifficultyLevel.BEGINNER,
            prompt = "Match each art tool to how artists use it!",
            promptBn = "আঁকার সরঞ্জামের সাথে কাজ মেলাও!",
            speechText = "Match each art tool to what it does.",
            correctAnswer = "ALL_MATCHED",
            hint = "Brush paints, Scissors snip paper, Crayon colors!",
            explanation = "Studio ready!",
            visualEmoji = "🖌️",
            matchPairs = listOf("Paintbrush 🖌️" to "Wet Paint", "Crayon 🖍️" to "Coloring Book", "Scissors ✂️" to "Paper Craft")
        ),
        LearningActivity(
            id = "art_9", worldId = WorldId.ART, title = "Rainbow Order",
            topicTag = "Identify Colors", type = ActivityType.FILL_BLANK, difficulty = DifficultyLevel.EXPLORER,
            prompt = "Complete the rainbow start: Red, Orange, ___, Green, Blue",
            promptBn = "রংধনুর রঙ: লাল, কমলা, ___, সবুজ, নীল!",
            speechText = "Red, Orange, blank, Green, Blue. Which color sits between Orange and Green?",
            options = listOf("Yellow", "Purple", "Brown", "Black"), correctAnswer = "Yellow",
            hint = "Bright like a banana!",
            explanation = "Red, Orange, Yellow, Green, Blue!",
            visualEmoji = "🌈"
        ),
        LearningActivity(
            id = "art_10", worldId = WorldId.ART, title = "Draw a Sailboat Shapes",
            topicTag = "Shape Building", type = ActivityType.MULTIPLE_CHOICE, difficulty = DifficultyLevel.BEGINNER,
            prompt = "To build a sailboat picture, what shape works best for the wind-catching sail?",
            promptBn = "পালতোলা নৌকার পাল আঁকতে কোন আকার সবচেয়ে ভালো?",
            speechText = "What shape makes the best sailboat sail?",
            options = listOf("Triangle 🔺", "Circle ⭕", "Oval 🥚", "Cross ➕"), correctAnswer = "Triangle 🔺",
            hint = "Pointy at the top and wide at the bottom!",
            explanation = "A crisp Triangle makes a classic sailboat sail!",
            visualEmoji = "⛵"
        ),
        LearningActivity(
            id = "art_11", worldId = WorldId.ART, title = "Creative Challenge: Clay Owl",
            topicTag = "Creative Challenges", type = ActivityType.MULTIPLE_CHOICE, difficulty = DifficultyLevel.EXPLORER,
            prompt = "You are sculpting a clay owl like Professor Hoot! What should you make biggest so it sees in the dark?",
            promptBn = "মাটির প্যাঁচা বানাতে কোন অংশটি বড় বানাতে হবে?",
            speechText = "When sculpting an owl, what feature is big and round?",
            options = listOf("Two big round eyes 👀", "A long elephant trunk", "Four car wheels", "Fish fins"),
            correctAnswer = "Two big round eyes 👀",
            hint = "Owls have famous big round eyes!",
            explanation = "Hoo-hoo! Big round eyes give owls great night vision!",
            visualEmoji = "🦉"
        ),
        LearningActivity(
            id = "art_12", worldId = WorldId.ART, title = "Complete the Face",
            topicTag = "Complete the Picture", type = ActivityType.MULTIPLE_CHOICE, difficulty = DifficultyLevel.BEGINNER,
            prompt = "Your drawn character has eyes and a nose. What curved line makes it look happy?",
            promptBn = "ছবির মুখটি হাসিখুশি দেখাতে কেমন রেখা আঁকবে?",
            speechText = "What curved line makes a face look happy?",
            options = listOf("An upward smile 😊", "A grumpy frown ☹️", "A zigzag lightning ⚡", "A dot"),
            correctAnswer = "An upward smile 😊",
            hint = "Curved up like a U!",
            explanation = "A gentle upward curve turns any doodle into a smiling friend!",
            visualEmoji = "😊"
        ),
        LearningActivity(
            id = "art_13", worldId = WorldId.ART, title = "Coloring Craft: Lighter Tint",
            topicTag = "Color Mixing", type = ActivityType.MULTIPLE_CHOICE, difficulty = DifficultyLevel.MASTER,
            prompt = "How can a painter turn dark Red ❤️ into soft pastel Pink 🩷?",
            promptBn = "গাঢ় লাল রঙকে কীভাবে হালকা গোলাপি (Pink) করা যায়?",
            speechText = "Which color do you mix with Red to make soft Pink?",
            options = listOf("Mix in White paint 🤍", "Mix in Black paint 🖤", "Mix in Green paint 💚", "Add mud"),
            correctAnswer = "Mix in White paint 🤍",
            hint = "White lightens colors into pastel shades!",
            explanation = "Red + White = Pretty Pink!",
            visualEmoji = "🩷"
        ),
        LearningActivity(
            id = "art_14", worldId = WorldId.ART, title = "Art Studio: Symmetry Check",
            topicTag = "Complete the Picture", type = ActivityType.MULTIPLE_CHOICE, difficulty = DifficultyLevel.MASTER,
            prompt = "Which letter looks the SAME on both left and right sides if you fold it down the middle?",
            promptBn = "কোন অক্ষরটিকে মাঝখান দিয়ে ভাঁজ করলে দুই পাশ সমান দেখায়?",
            speechText = "Which letter is symmetric down the middle: A, F, or G?",
            options = listOf("A", "F", "G", "R"), correctAnswer = "A",
            hint = "Draw a vertical line right down the tip of A!",
            explanation = "A has awesome vertical symmetry!",
            visualEmoji = "🪞"
        ),
        LearningActivity(
            id = "art_15", worldId = WorldId.ART, title = "Origami Paper Craft",
            topicTag = "Creative Challenges", type = ActivityType.MULTIPLE_CHOICE, difficulty = DifficultyLevel.EXPLORER,
            prompt = "What is the Japanese art of folding paper into cranes, boats, and frogs called?",
            promptBn = "কাগজ ভাঁজ করে পাখি বা নৌকা বানানোর শিল্পকে কী বলে?",
            speechText = "What is the art of paper folding called?",
            options = listOf("Origami", "Swimming", "Baking", "Gardening"), correctAnswer = "Origami",
            hint = "Ori means fold, kami means paper!",
            explanation = "Origami turns a single square sheet into magic!",
            visualEmoji = "🦢"
        )
    )

    val memoryActivities: List<LearningActivity> = listOf(
        LearningActivity(
            id = "mem_1", worldId = WorldId.MEMORY, title = "Fruit Card Match",
            topicTag = "Card Matching", type = ActivityType.MEMORY_CARDS, difficulty = DifficultyLevel.BEGINNER,
            prompt = "Flip the cards and find all 3 yummy fruit pairs!",
            promptBn = "কার্ড উল্টিয়ে ফলের জোড়া মেলাও!",
            speechText = "Flip cards to match the yummy fruit pairs!",
            correctAnswer = "ALL_FOUND",
            hint = "Remember where each fruit sits!",
            explanation = "Fruity memory power!",
            visualEmoji = "🍓",
            memoryPairs = listOf("🍎" to "🍎", "🍌" to "🍌", "🍓" to "🍓")
        ),
        LearningActivity(
            id = "mem_2", worldId = WorldId.MEMORY, title = "Animal Card Match",
            topicTag = "Card Matching", type = ActivityType.MEMORY_CARDS, difficulty = DifficultyLevel.BEGINNER,
            prompt = "Find all 4 hidden animal pairs!",
            promptBn = "৪টি প্রাণীর জোড়া খুঁজে বের করো!",
            speechText = "Flip the cards to match all 4 animal friends!",
            correctAnswer = "ALL_FOUND",
            hint = "Flip two cards at a time and remember their spots!",
            explanation = "Wildly awesome memory!",
            visualEmoji = "🦁",
            memoryPairs = listOf("🦁" to "🦁", "🐘" to "🐘", "🐰" to "🐰", "🐒" to "🐒")
        ),
        LearningActivity(
            id = "mem_3", worldId = WorldId.MEMORY, title = "Space Card Match",
            topicTag = "Card Matching", type = ActivityType.MEMORY_CARDS, difficulty = DifficultyLevel.EXPLORER,
            prompt = "Match all 4 outer space pairs!",
            promptBn = "মহাকাশের ৪টি জোড়া মেলাও!",
            speechText = "Match rockets, planets, moons, and stars!",
            correctAnswer = "ALL_FOUND",
            hint = "Take your time and picture the grid!",
            explanation = "Out-of-this-world memory!",
            visualEmoji = "🪐",
            memoryPairs = listOf("🚀" to "🚀", "🪐" to "🪐", "🌕" to "🌕", "⭐" to "⭐")
        ),
        LearningActivity(
            id = "mem_4", worldId = WorldId.MEMORY, title = "Ocean Card Match",
            topicTag = "Card Matching", type = ActivityType.MEMORY_CARDS, difficulty = DifficultyLevel.MASTER,
            prompt = "Deep dive! Match all 4 ocean creature pairs!",
            promptBn = "সাগরের ৪টি প্রাণীর জোড়া খুঁজে বের করো!",
            speechText = "Match whales, octopuses, crabs, and turtles!",
            correctAnswer = "ALL_FOUND",
            hint = "Keep track of each corner card!",
            explanation = "Splash-tastic concentration!",
            visualEmoji = "🐳",
            memoryPairs = listOf("🐳" to "🐳", "🐙" to "🐙", "🦀" to "🦀", "🐢" to "🐢")
        ),
        LearningActivity(
            id = "mem_5", worldId = WorldId.MEMORY, title = "Remember Sequence: Morning",
            topicTag = "Remember Sequence", type = ActivityType.SEQUENCE_RECALL, difficulty = DifficultyLevel.BEGINNER,
            prompt = "Memorize the 3 morning steps, then tap them in order!",
            promptBn = "সকালের ৩টি কাজের ক্রম মনে রেখে ট্যাপ করো!",
            speechText = "Memorize the morning sequence and tap the icons in order!",
            correctAnswer = "RECALLED",
            hint = "Sun wakes up, brush teeth, eat breakfast!",
            explanation = "Great morning focus!",
            visualEmoji = "🌅",
            sequenceItems = listOf("☀️", "🪥", "🥣")
        ),
        LearningActivity(
            id = "mem_6", worldId = WorldId.MEMORY, title = "Remember Sequence: Garden",
            topicTag = "Remember Sequence", type = ActivityType.SEQUENCE_RECALL, difficulty = DifficultyLevel.EXPLORER,
            prompt = "Watch the 4 garden steps grow, then repeat the exact order!",
            promptBn = "গাছ লাগানোর ৪টি ধাপ মনে রেখে সাজাও!",
            speechText = "Memorize the 4 gardening steps and tap them in order!",
            correctAnswer = "RECALLED",
            hint = "Seed → Water → Sun → Flower!",
            explanation = "Blooming memory!",
            visualEmoji = "🌻",
            sequenceItems = listOf("🌱", "💧", "☀️", "🌻")
        ),
        LearningActivity(
            id = "mem_7", worldId = WorldId.MEMORY, title = "Remember Sequence: Colors",
            topicTag = "Remember Sequence", type = ActivityType.SEQUENCE_RECALL, difficulty = DifficultyLevel.EXPLORER,
            prompt = "Memorize these 4 glowing gems in order!",
            promptBn = "৪টি রত্নের রঙের ক্রম মনে রাখো!",
            speechText = "Memorize the 4 color hearts in order!",
            correctAnswer = "RECALLED",
            hint = "Say the colors aloud as you watch!",
            explanation = "Rainbow recall champion!",
            visualEmoji = "💎",
            sequenceItems = listOf("❤️", "💛", "💚", "💙")
        ),
        LearningActivity(
            id = "mem_8", worldId = WorldId.MEMORY, title = "Remember Sequence: Master 5",
            topicTag = "Remember Sequence", type = ActivityType.SEQUENCE_RECALL, difficulty = DifficultyLevel.MASTER,
            prompt = "Master challenge! Memorize all 5 adventure icons in order!",
            promptBn = "৫টি আইকনের ক্রম মনে রেখে ট্যাপ করো!",
            speechText = "Memorize all 5 icons and repeat them in order!",
            correctAnswer = "RECALLED",
            hint = "Group the first 3 and last 2 in your head!",
            explanation = "Unbelievable memory master!",
            visualEmoji = "🧠",
            sequenceItems = listOf("🗺️", "🧭", "⛺", "🔦", "🏆")
        ),
        LearningActivity(
            id = "mem_9", worldId = WorldId.MEMORY, title = "Weather Card Match",
            topicTag = "Card Matching", type = ActivityType.MEMORY_CARDS, difficulty = DifficultyLevel.BEGINNER,
            prompt = "Match all 3 weather pairs!",
            promptBn = "আবহাওয়ার ৩টি জোড়া মেলাও!",
            speechText = "Flip and match sun, cloud, and rainbow!",
            correctAnswer = "ALL_FOUND",
            hint = "Look closely before each flip!",
            explanation = "Sunny memory!",
            visualEmoji = "⛅",
            memoryPairs = listOf("☀️" to "☀️", "🌧️" to "🌧️", "🌈" to "🌈")
        ),
        LearningActivity(
            id = "mem_10", worldId = WorldId.MEMORY, title = "Listen & Remember: Grocery List",
            topicTag = "Listen & Remember", type = ActivityType.AUDIO_LISTEN, difficulty = DifficultyLevel.EXPLORER,
            prompt = "Tap Listen! Mom asked for Milk, Bread, and Apples. Which item was NOT on Mom's list?",
            promptBn = "মন দিয়ে শোনো: কোন জিনিসটি তালিকায় ছিল না?",
            speechText = "Listen carefully! Please buy Milk, Bread, and Apples. Wait—which item was NOT on the list?",
            options = listOf("Toy Robot 🤖", "Milk 🥛", "Bread 🍞", "Apples 🍎"),
            correctAnswer = "Toy Robot 🤖",
            hint = "Milk, Bread, Apples were foods!",
            explanation = "Super listening! Toy Robot was not on the grocery list!",
            visualEmoji = "🛒"
        ),
        LearningActivity(
            id = "mem_11", worldId = WorldId.MEMORY, title = "Find Hidden Objects: Picnic",
            topicTag = "Find Hidden Objects", type = ActivityType.SPOT_DIFFERENCE, difficulty = DifficultyLevel.BEGINNER,
            prompt = "Find the 2 hidden picnic treats tucked among the leaves!",
            promptBn = "পাতার আড়ালে লুকানো ২টি খাবার খুঁজে বের করো!",
            speechText = "Find the 2 hidden picnic treats!",
            correctAnswer = "FOUND_ALL",
            hint = "Look for the cupcake and watermelon!",
            explanation = "Picnic treasure found!",
            visualEmoji = "🧺",
            differenceTargets = listOf("🧁 Cupcake", "🍉 Watermelon"),
            options = listOf("🍃 Leaf", "🧁 Cupcake", "🪨 Pebble", "🍉 Watermelon", "🌿 Fern", "🪵 Twig")
        ),
        LearningActivity(
            id = "mem_12", worldId = WorldId.MEMORY, title = "Picture Recall: Toy Box",
            topicTag = "Picture Recall", type = ActivityType.MULTIPLE_CHOICE, difficulty = DifficultyLevel.EXPLORER,
            prompt = "Picture check: Teddy Bear 🧸 sits ABOVE the Drum 🥁, and Ball ⚽ sits BELOW the Drum. What is in the middle?",
            promptBn = "টেডি বিয়ার ড্রামের উপরে আর বল ড্রামের নিচে থাকলে মাঝে কী আছে?",
            speechText = "Teddy Bear is above the Drum, and Ball is below the Drum. What is in the middle?",
            options = listOf("Drum 🥁", "Teddy Bear 🧸", "Ball ⚽", "Train 🚂"),
            correctAnswer = "Drum 🥁",
            hint = "Picture the 3 toys stacked top to bottom!",
            explanation = "Drum is right in the middle!",
            visualEmoji = "🥁"
        ),
        LearningActivity(
            id = "mem_13", worldId = WorldId.MEMORY, title = "Spot Differences: Bedroom",
            topicTag = "Spot the Differences", type = ActivityType.SPOT_DIFFERENCE, difficulty = DifficultyLevel.EXPLORER,
            prompt = "Spot the 2 tidy changes in Room B!",
            promptBn = "ঘর বি-তে যে ২টি জিনিস বদলেছে তা খুঁজে বের করো!",
            speechText = "Find the 2 differences in Room B!",
            correctAnswer = "FOUND_ALL",
            hint = "Look at the pillow and the clock!",
            explanation = "Sharp attention to detail!",
            visualEmoji = "🛏️",
            differenceTargets = listOf("🧸 Teddy (was 📚)", "⏰ Clock (was 🖼️)"),
            options = listOf("🛏️ Bed", "🧸 Teddy (was 📚)", "🪴 Plant", "⏰ Clock (was 🖼️)", "🪑 Chair", "💡 Lamp")
        ),
        LearningActivity(
            id = "mem_14", worldId = WorldId.MEMORY, title = "Listen & Remember: Pet Names",
            topicTag = "Listen & Remember", type = ActivityType.AUDIO_LISTEN, difficulty = DifficultyLevel.MASTER,
            prompt = "Tap Listen! 'Bunny is named Fluffy, Pup is named Max, Bird is named Kiwi.' What is the bird's name?",
            promptBn = "শোনার পর বলো: পাখিটির নাম কী?",
            speechText = "Bunny is named Fluffy, Pup is named Max, and Bird is named Kiwi. What is the bird's name?",
            options = listOf("Kiwi", "Fluffy", "Max", "Coco"), correctAnswer = "Kiwi",
            hint = "It sounds like a green fruit!",
            explanation = "Yes! Kiwi the singing bird!",
            visualEmoji = "🦜"
        ),
        LearningActivity(
            id = "mem_15", worldId = WorldId.MEMORY, title = "Music Note Match",
            topicTag = "Card Matching", type = ActivityType.MEMORY_CARDS, difficulty = DifficultyLevel.EXPLORER,
            prompt = "Flip and match all 3 musical instrument pairs!",
            promptBn = "বাদ্যযন্ত্রের ৩টি জোড়া মেলাও!",
            speechText = "Match the musical instrument cards!",
            correctAnswer = "ALL_FOUND",
            hint = "Guitar, Drum, Piano!",
            explanation = "Bravo, maestro!",
            visualEmoji = "🎸",
            memoryPairs = listOf("🎸" to "🎸", "🥁" to "🥁", "🎹" to "🎹")
        )
    )

    val realLifeActivities: List<LearningActivity> = listOf(
        LearningActivity(
            id = "rl_1", worldId = WorldId.REAL_LIFE, title = "Personal Hygiene: Toothbrush",
            topicTag = "Personal Hygiene", type = ActivityType.MULTIPLE_CHOICE, difficulty = DifficultyLevel.BEGINNER,
            prompt = "How many times a day should we brush our teeth to keep cavities away?",
            promptBn = "দাঁত ভালো রাখতে দিনে কয়বার ব্রাশ করা উচিত?",
            speechText = "How many times each day should we brush our teeth?",
            options = listOf("Twice (morning & night)", "Once a month", "Only on birthdays", "Never"),
            correctAnswer = "Twice (morning & night)",
            hint = "After breakfast and right before bedtime!",
            explanation = "Brushing morning and night keeps your smile sparkling!",
            visualEmoji = "🪥"
        ),
        LearningActivity(
            id = "rl_2", worldId = WorldId.REAL_LIFE, title = "Road Safety: Zebra Crossing",
            topicTag = "Road Safety", type = ActivityType.MULTIPLE_CHOICE, difficulty = DifficultyLevel.BEGINNER,
            prompt = "Before crossing a street with a parent, what should you always do?",
            promptBn = "বড়দের সাথে রাস্তা পার হওয়ার আগে কী দেখতে হবে?",
            speechText = "Before crossing a street with an adult, what should you always do?",
            options = listOf("Stop & look left, right, left", "Run without looking", "Close your eyes", "Look only at shoes"),
            correctAnswer = "Stop & look left, right, left",
            hint = "Hold hands and check both ways for cars!",
            explanation = "Looking left, right, and left again keeps you super safe!",
            visualEmoji = "🚸"
        ),
        LearningActivity(
            id = "rl_3", worldId = WorldId.REAL_LIFE, title = "Healthy Plate Sort",
            topicTag = "Healthy Habits", type = ActivityType.DRAG_SORT, difficulty = DifficultyLevel.BEGINNER,
            prompt = "Sort snacks into Everyday Healthy Foods and Occasional Treats!",
            promptBn = "প্রতিদিনের স্বাস্থ্যকর খাবার আর মাঝে মাঝে খাওয়ার খাবার আলাদা করো!",
            speechText = "Sort foods into Everyday Healthy and Occasional Treat.",
            correctAnswer = "ALL_SORTED",
            hint = "Fruits and carrots give strong energy every day!",
            explanation = "Balanced eating gives you superhero energy!",
            visualEmoji = "🥗",
            buckets = listOf("Everyday Healthy", "Occasional Treat"),
            dragItems = listOf("Apple 🍎" to "Everyday Healthy", "Candy 🍬" to "Occasional Treat", "Broccoli 🥦" to "Everyday Healthy", "Cupcake 🧁" to "Occasional Treat")
        ),
        LearningActivity(
            id = "rl_4", worldId = WorldId.REAL_LIFE, title = "Sharing & Kindness",
            topicTag = "Sharing & Kindness", type = ActivityType.MULTIPLE_CHOICE, difficulty = DifficultyLevel.BEGINNER,
            prompt = "A new classmate looks shy during playtime. What is the kindest thing to do?",
            promptBn = "নতুন বন্ধু চুপচাপ দাঁড়িয়ে থাকলে তোমার কী করা উচিত?",
            speechText = "A new classmate looks shy at playtime. What is the kindest choice?",
            options = listOf("Smile and invite them to play", "Ignore them", "Laugh at them", "Hide all the toys"),
            correctAnswer = "Smile and invite them to play",
            hint = "Kindness turns strangers into best friends!",
            explanation = "Inviting others to join makes everyone feel welcome!",
            visualEmoji = "🤝"
        ),
        LearningActivity(
            id = "rl_5", worldId = WorldId.REAL_LIFE, title = "Smart Shopping & Money",
            topicTag = "Shopping & Money", type = ActivityType.MULTIPLE_CHOICE, difficulty = DifficultyLevel.EXPLORER,
            prompt = "You have 10 coins. A storybook costs 7 coins and a shiny sticker costs 5 coins. Can you buy BOTH?",
            promptBn = "তোমার কাছে ১০ টাকা আছে। বই ৭ টাকা ও স্টিকার ৫ টাকা হলে কি দুটিই কেনা যাবে?",
            speechText = "You have 10 coins. A book is 7 and a sticker is 5. Can you buy both?",
            options = listOf("No, 7 + 5 = 12 coins", "Yes, 7 + 5 = 8 coins", "Yes, they are free", "No, 7 + 5 = 20 coins"),
            correctAnswer = "No, 7 + 5 = 12 coins",
            hint = "Add 7 + 5 and compare to 10!",
            explanation = "Smart budgeting! 12 is more than 10, so pick your favorite or save 2 more coins!",
            visualEmoji = "🛍️"
        ),
        LearningActivity(
            id = "rl_6", worldId = WorldId.REAL_LIFE, title = "Time Management: Homework First",
            topicTag = "Time Management", type = ActivityType.MULTIPLE_CHOICE, difficulty = DifficultyLevel.EXPLORER,
            prompt = "You want to play games AND finish a 10-minute reading task before dinner. What is best?",
            promptBn = "খেলা আর পড়া দুটোই করতে হলে আগে কোনটি করা ভালো?",
            speechText = "Which should come first so you can play worry-free: finishing your quick reading or delaying until bedtime?",
            options = listOf("Finish 10-min reading first, then play", "Skip reading forever", "Wait until midnight", "Do neither"),
            correctAnswer = "Finish 10-min reading first, then play",
            hint = "Work first, then relaxed play!",
            explanation = "Finishing tasks first makes playtime extra fun!",
            visualEmoji = "⏳"
        ),
        LearningActivity(
            id = "rl_7", worldId = WorldId.REAL_LIFE, title = "Helping Others at Home",
            topicTag = "Helping Others", type = ActivityType.MULTIPLE_CHOICE, difficulty = DifficultyLevel.BEGINNER,
            prompt = "After finishing building with blocks, what is a helpful habit?",
            promptBn = "খেলা শেষে খেলনাগুলো নিয়ে কী করা ভালো অভ্যাস?",
            speechText = "After playing with blocks, what is a helpful habit?",
            options = listOf("Put toys neatly back in the bin", "Leave them on the stairs", "Hide them under pillows", "Break them"),
            correctAnswer = "Put toys neatly back in the bin",
            hint = "Keeping floors clear prevents tripping!",
            explanation = "Tidy explorer! Putting toys away keeps your home safe and neat!",
            visualEmoji = "🧸"
        ),
        LearningActivity(
            id = "rl_8", worldId = WorldId.REAL_LIFE, title = "Helmet Safety",
            topicTag = "Road Safety", type = ActivityType.MULTIPLE_CHOICE, difficulty = DifficultyLevel.BEGINNER,
            prompt = "Whenever you ride a bicycle or scooter, what protects your head?",
            promptBn = "সাইকেল চালানোর সময় মাথা নিরাপদ রাখতে কী পরতে হয়?",
            speechText = "What safety gear protects your head when riding a bike?",
            options = listOf("A buckled helmet 🪖", "Sunglasses 🕶️", "Sandals 🩴", "A scarf 🧣"),
            correctAnswer = "A buckled helmet 🪖",
            hint = "It snaps safely under your chin!",
            explanation = "Helmets protect your amazing brain!",
            visualEmoji = "🚲"
        ),
        LearningActivity(
            id = "rl_9", worldId = WorldId.REAL_LIFE, title = "Needs vs Wants",
            topicTag = "Shopping & Money", type = ActivityType.DRAG_SORT, difficulty = DifficultyLevel.EXPLORER,
            prompt = "Sort Needs (must have to live) and Wants (nice extras)!",
            promptBn = "প্রয়োজন (Need) আর শখ (Want) আলাদা করো!",
            speechText = "Sort items into Needs and Wants.",
            correctAnswer = "ALL_SORTED",
            hint = "Clean water and warm coats are Needs; balloons and video games are Wants!",
            explanation = "Financial wisdom! Knowing Needs vs Wants is a superpower!",
            visualEmoji = "🪙",
            buckets = listOf("Needs", "Wants"),
            dragItems = listOf("Clean Water 💧" to "Needs", "Toy Car 🏎️" to "Wants", "Warm Coat 🧥" to "Needs", "Balloon 🎈" to "Wants")
        ),
        LearningActivity(
            id = "rl_10", worldId = WorldId.REAL_LIFE, title = "Sneeze Etiquette",
            topicTag = "Personal Hygiene", type = ActivityType.MULTIPLE_CHOICE, difficulty = DifficultyLevel.BEGINNER,
            prompt = "When you feel a sneeze coming, where should you sneeze to protect friends from germs?",
            promptBn = "হাঁচি এলে কোথায় মুখ ঢেকে হাঁচি দেবে?",
            speechText = "Where should you sneeze so germs do not spread?",
            options = listOf("Into a tissue or your elbow", "On your friend's lunch", "Into the air", "On your hands without washing"),
            correctAnswer = "Into a tissue or your elbow",
            hint = "Make a superhero wing with your bent elbow!",
            explanation = "Coughing or sneezing into your elbow stops germs in their tracks!",
            visualEmoji = "🤧"
        ),
        LearningActivity(
            id = "rl_11", worldId = WorldId.REAL_LIFE, title = "Asking Permission",
            topicTag = "Decision Making", type = ActivityType.MULTIPLE_CHOICE, difficulty = DifficultyLevel.EXPLORER,
            prompt = "You find a shiny eraser on your friend's desk. What is the honest decision?",
            promptBn = "বন্ধুর ডেস্ক থেকে কিছু পেলে বা নিতে চাইলে কী করা উচিত?",
            speechText = "You see a cool eraser on a friend's desk. What should you do?",
            options = listOf("Leave it or ask nicely first", "Put it in your pocket secretly", "Draw on it", "Throw it away"),
            correctAnswer = "Leave it or ask nicely first",
            hint = "Honesty builds lasting trust!",
            explanation = "Asking permission shows respect and honesty!",
            visualEmoji = "🌟"
        ),
        LearningActivity(
            id = "rl_12", worldId = WorldId.REAL_LIFE, title = "Sleep & Energy",
            topicTag = "Healthy Habits", type = ActivityType.MULTIPLE_CHOICE, difficulty = DifficultyLevel.BEGINNER,
            prompt = "Why do children need 9–11 hours of peaceful sleep every night?",
            promptBn = "রাতে পর্যাপ্ত ঘুম কেন জরুরি?",
            speechText = "Why do growing kids need good sleep every night?",
            options = listOf("To grow strong and help the brain remember", "To miss breakfast", "To stay grumpy", "To forget shapes"),
            correctAnswer = "To grow strong and help the brain remember",
            hint = "Your body recharges like a battery while you dream!",
            explanation = "Sleep charges your brain battery for tomorrow's adventures!",
            visualEmoji = "😴"
        ),
        LearningActivity(
            id = "rl_13", worldId = WorldId.REAL_LIFE, title = "Good Habits Match",
            topicTag = "Healthy Habits", type = ActivityType.PAIR_MATCH, difficulty = DifficultyLevel.BEGINNER,
            prompt = "Match each daily time to its healthy habit!",
            promptBn = "দিনের সময়ের সাথে ভালো অভ্যাস মেলাও!",
            speechText = "Match each part of the day with its healthy routine.",
            correctAnswer = "ALL_MATCHED",
            hint = "Wash hands before eating, brush before bed!",
            explanation = "Routine rockstar!",
            visualEmoji = "⏰",
            matchPairs = listOf("Morning 🌅" to "Eat Breakfast 🥣", "Before Meals 🍽️" to "Wash Hands 🧼", "Bedtime 🌙" to "Read & Sleep 📖")
        ),
        LearningActivity(
            id = "rl_14", worldId = WorldId.REAL_LIFE, title = "Saving Water & Electricity",
            topicTag = "Helping Others", type = ActivityType.MULTIPLE_CHOICE, difficulty = DifficultyLevel.EXPLORER,
            prompt = "You are the last person leaving the playroom. What should you do with the lights?",
            promptBn = "ঘর থেকে সবার শেষে বের হওয়ার সময় লাইটের সুইচ নিয়ে কী করবে?",
            speechText = "When you leave an empty room, what should you do with the lights?",
            options = listOf("Switch the lights off 💡", "Leave them on all night", "Turn on extra fans", "Open the fridge door"),
            correctAnswer = "Switch the lights off 💡",
            hint = "Saving energy helps your family and Earth!",
            explanation = "Energy hero! Switching off unused lights saves power!",
            visualEmoji = "💡"
        ),
        LearningActivity(
            id = "rl_15", worldId = WorldId.REAL_LIFE, title = "Calm Problem Solving",
            topicTag = "Decision Making", type = ActivityType.MULTIPLE_CHOICE, difficulty = DifficultyLevel.MASTER,
            prompt = "Two friends both want to use the red crayon at the exact same moment. What is a fair solution?",
            promptBn = "দুই বন্ধু একই সাথে লাল রঙ চাইলে সবচেয়ে সুন্দর সমাধান কী?",
            speechText = "Two friends want the same red crayon. What is the fairest solution?",
            options = listOf("Take turns for 5 minutes each", "Pull it until it snaps", "Shout loudly", "Hide the crayon box"),
            correctAnswer = "Take turns for 5 minutes each",
            hint = "Sharing and taking turns lets everyone finish their art!",
            explanation = "Taking turns is fair and keeps friendships strong!",
            visualEmoji = "🖍️"
        ),
        LearningActivity(
            id = "rl_16", worldId = WorldId.REAL_LIFE, title = "Emergency Numbers",
            topicTag = "Road Safety", type = ActivityType.MULTIPLE_CHOICE, difficulty = DifficultyLevel.MASTER,
            prompt = "If someone is lost in a store, who is a safe grown-up to ask for help?",
            promptBn = "দোকানে বড়দের থেকে আলাদা হয়ে গেলে কার সাহায্য নেওয়া নিরাপদ?",
            speechText = "If you ever get separated from your parent in a store, who should you ask for help?",
            options = listOf("A uniformed store worker or security desk", "Run out into the parking lot", "Leave the building alone", "Hide inside a rack"),
            correctAnswer = "A uniformed store worker or security desk",
            hint = "Stay inside the building and look for a helper badge!",
            explanation = "Smart safety rule: stay put inside and find a uniformed worker or parent with kids!",
            visualEmoji = "🛡️"
        )
    )

    val badges: List<BadgeDef> = listOf(
        BadgeDef("badge_first_step", "First Spark", "Complete your very first learning activity!", "🌟", requiredStars = 1),
        BadgeDef("badge_five_stars", "Rising Explorer", "Collect 5 stars across any worlds!", "🚀", requiredStars = 5),
        BadgeDef("badge_fifteen_stars", "Knowledge Wizard", "Collect 15 stars across worlds!", "🧙", requiredStars = 15),
        BadgeDef("badge_thirty_stars", "Super Champion", "Collect 30 stars on your adventure!", "🏆", requiredStars = 30),
        BadgeDef("badge_english", "Word Explorer", "Complete 3+ activities in English World", "🔤", requiredWorld = WorldId.ENGLISH),
        BadgeDef("badge_math", "Number Hero", "Complete 3+ activities in Math World", "🔢", requiredWorld = WorldId.MATH),
        BadgeDef("badge_bangla", "বাংলা বন্ধু", "Complete 3+ activities in Bangla World", "🇧🇩", requiredWorld = WorldId.BANGLA),
        BadgeDef("badge_science", "Junior Scientist", "Complete 3+ activities in Science World", "🔬", requiredWorld = WorldId.SCIENCE),
        BadgeDef("badge_gk", "Globe Trotter", "Complete 3+ activities in World GK", "🌍", requiredWorld = WorldId.GK),
        BadgeDef("badge_logic", "Puzzle Master", "Complete 3+ activities in Logic World", "🧩", requiredWorld = WorldId.LOGIC),
        BadgeDef("badge_story", "Story Dreamer", "Finish an Interactive Story Adventure", "📖", requiredWorld = WorldId.STORY),
        BadgeDef("badge_art", "Rainbow Artist", "Complete 3+ activities in Art World", "🎨", requiredWorld = WorldId.ART),
        BadgeDef("badge_memory", "Memory Ace", "Complete 3+ activities in Memory World", "🧠", requiredWorld = WorldId.MEMORY),
        BadgeDef("badge_reallife", "Kind Citizen", "Complete 3+ activities in Real-Life World", "💚", requiredWorld = WorldId.REAL_LIFE)
    )

    val unlockableCharacters: List<UnlockableCharacter> = listOf(
        UnlockableCharacter("owl_hoot", "Professor Hoot", "🦉", "Your wise & cheerful AI Owl Teacher", 0),
        UnlockableCharacter("bunny_pip", "Brave Rabbit", "🐰", "Quick-thinking forest explorer", 50),
        UnlockableCharacter("lion_leo", "Friendly Lion", "🦁", "Warm-hearted protector of the savanna", 120),
        UnlockableCharacter("monkey_milo", "Clever Monkey", "🐒", "Loves math bananas and puzzles", 200),
        UnlockableCharacter("elephant_ellie", "Talking Elephant", "🐘", "Never forgets a single word!", 300),
        UnlockableCharacter("explorer_aya", "Little Explorer", "🧭", "Ready to map all 7 continents!", 450)
    )
}
