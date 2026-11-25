package com.example.data.content

import com.example.data.model.ActivityType
import com.example.data.model.DifficultyLevel
import com.example.data.model.LearningActivity
import com.example.data.model.WorldId

object ContentCatalogPart2 {
    val banglaActivities: List<LearningActivity> = listOf(
        LearningActivity(
            id = "bn_1", worldId = WorldId.BANGLA, title = "বাংলা স্বরবর্ণ: অ-এ অজগর",
            topicTag = "স্বরবর্ণ", type = ActivityType.AUDIO_LISTEN, difficulty = DifficultyLevel.BEGINNER,
            prompt = "শোনো ও বলো: বাংলা স্বরবর্ণের প্রথম অক্ষর কোনটি?",
            promptBn = "অ-এ অজগর আসছে তেড়ে!",
            speechText = "অ-এ অজগর। বাংলা স্বরবর্ণের প্রথম অক্ষর অ।",
            options = listOf("অ", "আ", "ক", "খ"), correctAnswer = "অ",
            hint = "স্বরবর্ণ শুরু হয় 'অ' দিয়ে!",
            explanation = "দারুণ! স্বরবর্ণের প্রথম অক্ষর হলো 'অ'!",
            visualEmoji = "🐍", isVocabulary = true
        ),
        LearningActivity(
            id = "bn_2", worldId = WorldId.BANGLA, title = "আ-এ আম",
            topicTag = "স্বরবর্ণ", type = ActivityType.PICTURE_MATCH, difficulty = DifficultyLevel.BEGINNER,
            prompt = "ছবি দেখে বলো: 'আম' শব্দটি কোন অক্ষর দিয়ে শুরু?",
            promptBn = "মিষ্টি আম 🥭",
            speechText = "আ-এ আম! আম কোন বর্ণ দিয়ে শুরু?",
            options = listOf("আ", "ই", "উ", "ম"), correctAnswer = "আ",
            hint = "অ-এর পাশে একটি আকার থাকে!",
            explanation = "শাবাশ! আ-এ আম!",
            visualEmoji = "🥭", isVocabulary = true
        ),
        LearningActivity(
            id = "bn_3", worldId = WorldId.BANGLA, title = "ই-এ ইলিশ",
            topicTag = "স্বরবর্ণ", type = ActivityType.MULTIPLE_CHOICE, difficulty = DifficultyLevel.BEGINNER,
            prompt = "আমাদের জাতীয় মাছ 'ইলিশ' কোন বর্ণ দিয়ে শুরু হয়?",
            promptBn = "ই-এ ইলিশ ভাজা খেতে মজা!",
            speechText = "ইলিশ মাছ কোন স্বরবর্ণ দিয়ে শুরু?",
            options = listOf("ই", "ঈ", "উ", "এ"), correctAnswer = "ই",
            hint = "হ্রস্ব-ই দিয়ে ইলিশ!",
            explanation = "একদম ঠিক! ই-এ ইলিশ!",
            visualEmoji = "🐟", isVocabulary = true
        ),
        LearningActivity(
            id = "bn_4", worldId = WorldId.BANGLA, title = "ব্যঞ্জনবর্ণ: ক-এ কলা",
            topicTag = "ব্যঞ্জনবর্ণ", type = ActivityType.PICTURE_MATCH, difficulty = DifficultyLevel.BEGINNER,
            prompt = "কলা 🍌 শব্দটির প্রথম ব্যঞ্জনবর্ণ কোনটি?",
            promptBn = "ক-এ কলা!",
            speechText = "কলা শব্দের প্রথম অক্ষর ক।",
            options = listOf("ক", "খ", "গ", "ঘ"), correctAnswer = "ক",
            hint = "ব্যঞ্জনবর্ণের প্রথম অক্ষর!",
            explanation = "চমৎকার! ক-এ কলা!",
            visualEmoji = "🍌", isVocabulary = true
        ),
        LearningActivity(
            id = "bn_5", worldId = WorldId.BANGLA, title = "স্বরবর্ণ ও ব্যঞ্জনবর্ণ আলাদা করো",
            topicTag = "বর্ণমালা", type = ActivityType.DRAG_SORT, difficulty = DifficultyLevel.BEGINNER,
            prompt = "স্বরবর্ণ ও ব্যঞ্জনবর্ণ সঠিক ঝুড়িতে রাখো!",
            promptBn = "স্বরবর্ণ (অ, আ) আর ব্যঞ্জনবর্ণ (ক, খ) আলাদা করো।",
            speechText = "স্বরবর্ণ ও ব্যঞ্জনবর্ণ আলাদা ঝুড়িতে রাখো।",
            correctAnswer = "ALL_SORTED",
            hint = "অ এবং আ হলো স্বরবর্ণ। ক এবং খ হলো ব্যঞ্জনবর্ণ!",
            explanation = "খুব সুন্দর! তুমি বাংলা বর্ণমালা চিনে গেছো!",
            visualEmoji = "🧺", isVocabulary = true,
            buckets = listOf("স্বরবর্ণ", "ব্যঞ্জনবর্ণ"),
            dragItems = listOf("অ" to "স্বরবর্ণ", "ক" to "ব্যঞ্জনবর্ণ", "আ" to "স্বরবর্ণ", "খ" to "ব্যঞ্জনবর্ণ")
        ),
        LearningActivity(
            id = "bn_6", worldId = WorldId.BANGLA, title = "অক্ষর সাজাও: ফল",
            topicTag = "শব্দ গঠন", type = ActivityType.WORD_BUILD, difficulty = DifficultyLevel.BEGINNER,
            prompt = "বর্ণ সাজিয়ে 'ফল' শব্দটি তৈরি করো! 🍇",
            promptBn = "ফ + ল = ফল",
            speechText = "ফ আর ল দিয়ে ফল শব্দটি সাজাও।",
            options = listOf("ল", "ফ"), correctAnswer = "ফল",
            hint = "প্রথমে 'ফ', তারপর 'ল'!",
            explanation = "বাহ! ফ + ল = ফল!",
            visualEmoji = "🍇", isVocabulary = true
        ),
        LearningActivity(
            id = "bn_7", worldId = WorldId.BANGLA, title = "অক্ষর সাজাও: ফুল",
            topicTag = "শব্দ গঠন", type = ActivityType.WORD_BUILD, difficulty = DifficultyLevel.BEGINNER,
            prompt = "বর্ণ সাজিয়ে 'ফুল' শব্দটি তৈরি করো! 🌸",
            promptBn = "ফু + ল = ফুল",
            speechText = "ফু আর ল সাজিয়ে ফুল শব্দ তৈরি করো।",
            options = listOf("ল", "ফু"), correctAnswer = "ফুল",
            hint = "প্রথমে 'ফু', তারপর 'ল'!",
            explanation = "সুন্দর! ফু + ল = ফুল!",
            visualEmoji = "🌸", isVocabulary = true
        ),
        LearningActivity(
            id = "bn_8", worldId = WorldId.BANGLA, title = "ছবি ও শব্দ মেলাও",
            topicTag = "ছবি দেখে শব্দ", type = ActivityType.PAIR_MATCH, difficulty = DifficultyLevel.BEGINNER,
            prompt = "বাংলা শব্দের সাথে ছবির জুটি মেলাও!",
            promptBn = "বই, চাঁদ ও পাখি মেলাও!",
            speechText = "ছবির সাথে সঠিক বাংলা শব্দ মেলাও।",
            correctAnswer = "ALL_MATCHED",
            hint = "বই হলো পড়ার জিনিস, চাঁদ ওঠে রাতে!",
            explanation = "অসাধারণ! সব জুটি মিলেছে!",
            visualEmoji = "🌙", isVocabulary = true,
            matchPairs = listOf("বই" to "📘", "চাঁদ" to "🌙", "পাখি" to "🐦")
        ),
        LearningActivity(
            id = "bn_9", worldId = WorldId.BANGLA, title = "শূন্যস্থান পূরণ করো",
            topicTag = "বানান", type = ActivityType.FILL_BLANK, difficulty = DifficultyLevel.EXPLORER,
            prompt = "শূন্যস্থানে সঠিক বর্ণ বসাও: বা__লাদেশ 🇧🇩",
            promptBn = "আমাদের প্রিয় দেশ!",
            speechText = "বাংলাদেশ বানানের মাঝের অক্ষর কোনটি?",
            options = listOf("ং", "ত", "ম", "র"), correctAnswer = "ং",
            hint = "অনুস্বার (ং) বসালে হয় বাংলাদেশ!",
            explanation = "সঠিক! বাংলাদেশ আমাদের প্রিয় মাতৃভূমি!",
            visualEmoji = "🇧🇩", isVocabulary = true
        ),
        LearningActivity(
            id = "bn_10", worldId = WorldId.BANGLA, title = "সঠিক বানান খুঁজো",
            topicTag = "বানান", type = ActivityType.MULTIPLE_CHOICE, difficulty = DifficultyLevel.EXPLORER,
            prompt = "নিচের কোন শব্দটির বানান সঠিক?",
            promptBn = "সবুজ পাতা ভরা 'গাছ' কোন বানানটি ঠিক?",
            speechText = "গাছ শব্দের সঠিক বানান কোনটি?",
            options = listOf("গাছ", "গাচ", "গাশ", "গাঝ"), correctAnswer = "গাছ",
            hint = "গ-আকার গা, তারপর ছ!",
            explanation = "ঠিক ধরেছো! গাছ মানে tree!",
            visualEmoji = "🌳", isVocabulary = true
        ),
        LearningActivity(
            id = "bn_11", worldId = WorldId.BANGLA, title = "বাক্য গঠন",
            topicTag = "বাক্য গঠন", type = ActivityType.FILL_BLANK, difficulty = DifficultyLevel.EXPLORER,
            prompt = "বাক্যটি পূর্ণ করো: 'আমি প্রতিদিন ___ পড়ি।'",
            promptBn = "সঠিক শব্দ বেছে বাক্য তৈরি করো।",
            speechText = "আমি প্রতিদিন কি পড়ি?",
            options = listOf("বই", "জল", "রাস্তা", "মেঘ"), correctAnswer = "বই",
            hint = "আমরা কী পড়ি?",
            explanation = "চমৎকার! আমি প্রতিদিন বই পড়ি।",
            visualEmoji = "📖", isVocabulary = true
        ),
        LearningActivity(
            id = "bn_12", worldId = WorldId.BANGLA, title = "বাক্য সাজাই: সূর্য ওঠে",
            topicTag = "বাক্য গঠন", type = ActivityType.MULTIPLE_CHOICE, difficulty = DifficultyLevel.EXPLORER,
            prompt = "সকালে পূর্ব আকাশে কী ওঠে?",
            promptBn = "উত্তরটি বাংলায় বেছে নাও।",
            speechText = "সকালে পূর্ব আকাশে কী ওঠে?",
            options = listOf("সূর্য", "চাঁদ", "তারা", "বৃষ্টি"), correctAnswer = "সূর্য",
            hint = "সূর্য আমাদের আলো ও তাপ দেয়!",
            explanation = "হ্যাঁ! সকালে সূর্য ওঠে!",
            visualEmoji = "🌅", isVocabulary = true
        ),
        LearningActivity(
            id = "bn_13", worldId = WorldId.BANGLA, title = "ছোট গল্প পড়া: টিয়া ও টুনটুনি",
            topicTag = "ছোট গল্প", type = ActivityType.MULTIPLE_CHOICE, difficulty = DifficultyLevel.MASTER,
            prompt = "গল্প: 'সবুজ টিয়া আর ছোট্ট টুনটুনি মিলে আম বাগানে গান গাইছিল।' পাখিরা কোথায় গান গাইছিল?",
            promptBn = "গল্পটি পড়ে সঠিক উত্তর দাও।",
            speechText = "সবুজ টিয়া আর ছোট্ট টুনটুনি কোথায় গান গাইছিল?",
            options = listOf("আম বাগানে", "নদীর নিচে", "আকাশের চাঁদে", "রেলগাড়িতে"), correctAnswer = "আম বাগানে",
            hint = "গল্পের মাঝখানে বাগানের নাম আছে!",
            explanation = "খুব ভালো পড়া! পাখিরা আম বাগানে গান গাইছিল!",
            visualEmoji = "🦜", isVocabulary = true
        ),
        LearningActivity(
            id = "bn_14", worldId = WorldId.BANGLA, title = "বাংলা সংখ্যা: ১ থেকে ৫",
            topicTag = "বাংলা সংখ্যা", type = ActivityType.PAIR_MATCH, difficulty = DifficultyLevel.BEGINNER,
            prompt = "বাংলা সংখ্যার সাথে ফলের সংখ্যা মেলাও!",
            promptBn = "১, ২, ৩ মেলাও!",
            speechText = "বাংলা সংখ্যার সাথে ফলের জুটি মেলাও।",
            correctAnswer = "ALL_MATCHED",
            hint = "১ মানে একটি, ২ মানে দুটি!",
            explanation = "দারুণ বাংলা সংখ্যা চেনা!",
            visualEmoji = "🍓", isVocabulary = true,
            matchPairs = listOf("১" to "🍎", "২" to "🍎🍎", "৩" to "🍎🍎🍎")
        ),
        LearningActivity(
            id = "bn_15", worldId = WorldId.BANGLA, title = "উচ্চারণ ও বলা: ধন্যবাদ",
            topicTag = "বলা ও শোনা", type = ActivityType.VOICE_SPEAK, difficulty = DifficultyLevel.BEGINNER,
            prompt = "স্পিক বোতামে চাপ দিয়ে বলো: 'ধন্যবাদ' (বা টাইপ করো 'thank you' / 'ধন্যবাদ')",
            promptBn = "ভদ্রতার সুন্দর বাংলা শব্দ: ধন্যবাদ!",
            speechText = "ধন্যবাদ! বাংলায় মিষ্টি করে বলো ধন্যবাদ।",
            correctAnswer = "ধন্যবাদ",
            hint = "মাইকে 'ধন্যবাদ' বলো অথবা বক্সে লিখো!",
            explanation = "খুব মিষ্টি শোনা গেল! সবাইকে ধন্যবাদ বলা দারুণ অভ্যাস!",
            visualEmoji = "🙏", isVocabulary = true
        ),
        LearningActivity(
            id = "bn_16", worldId = WorldId.BANGLA, title = "অক্ষর সাজাও: পাখি",
            topicTag = "শব্দ গঠন", type = ActivityType.WORD_BUILD, difficulty = DifficultyLevel.EXPLORER,
            prompt = "বর্ণ সাজিয়ে 'পাখি' শব্দটি তৈরি করো! 🐦",
            promptBn = "পা + খি = পাখি",
            speechText = "পা আর খি দিয়ে পাখি শব্দ বানাও।",
            options = listOf("খি", "পা"), correctAnswer = "পাখি",
            hint = "প্রথমে 'পা', তারপর 'খি'!",
            explanation = "চমৎকার! পা + খি = পাখি!",
            visualEmoji = "🐦", isVocabulary = true
        ),
        LearningActivity(
            id = "bn_17", worldId = WorldId.BANGLA, title = "বাংলা রঙ চেনা",
            topicTag = "রঙ ও শব্দ", type = ActivityType.MULTIPLE_CHOICE, difficulty = DifficultyLevel.BEGINNER,
            prompt = "বাংলাদেশের পতাকার মাঝের গোল বৃত্তটির রঙ কী?",
            promptBn = "আমাদের পতাকা সবুজ আর ___!",
            speechText = "বাংলাদেশের পতাকার মাঝের বৃত্তের রঙ কী?",
            options = listOf("লাল", "নীল", "হলুদ", "সাদা"), correctAnswer = "লাল",
            hint = "ভোরের সূর্যের মতো লাল!",
            explanation = "সবুজের বুকে লাল সূর্য!",
            visualEmoji = "🔴", isVocabulary = true
        ),
        LearningActivity(
            id = "bn_18", worldId = WorldId.BANGLA, title = "ছোট গল্প পড়া: দাদুর বাগান",
            topicTag = "ছোট গল্প", type = ActivityType.MULTIPLE_CHOICE, difficulty = DifficultyLevel.MASTER,
            prompt = "গল্প: 'রাহুল প্রতিদিন সকালে দাদুর বাগানের গাছে পানি দেয়।' রাহুল কখন গাছে পানি দেয়?",
            promptBn = "গল্প থেকে সঠিক সময় বেছে নাও।",
            speechText = "রাহুল কখন দাদুর বাগানের গাছে পানি দেয়?",
            options = listOf("প্রতিদিন সকালে", "গভীর রাতে", "শুধু শীতে", "কখনোই না"), correctAnswer = "প্রতিদিন সকালে",
            hint = "প্রথম লাইনেই উত্তর আছে!",
            explanation = "শাবাশ! গাছে পানি দেওয়া দারুণ ভালো কাজ!",
            visualEmoji = "🪴", isVocabulary = true
        )
    )

    val scienceActivities: List<LearningActivity> = listOf(
        LearningActivity(
            id = "sci_1", worldId = WorldId.SCIENCE, title = "Five Senses: Seeing",
            topicTag = "Five Senses", type = ActivityType.MULTIPLE_CHOICE, difficulty = DifficultyLevel.BEGINNER,
            prompt = "Which part of our body helps us SEE colorful rainbows?",
            promptBn = "আমরা শরীরের কোন অংশ দিয়ে রংধনু দেখি?",
            speechText = "Which part of your body helps you see rainbows?",
            options = listOf("Eyes 👀", "Ears 👂", "Nose 👃", "Hands ✋"), correctAnswer = "Eyes 👀",
            hint = "You blink them!",
            explanation = "Yes! Our eyes give us the sense of sight!",
            visualEmoji = "👀", isVocabulary = true
        ),
        LearningActivity(
            id = "sci_2", worldId = WorldId.SCIENCE, title = "Five Senses Match",
            topicTag = "Five Senses", type = ActivityType.PAIR_MATCH, difficulty = DifficultyLevel.BEGINNER,
            prompt = "Match each sense organ to what it senses!",
            promptBn = "ইন্দ্রিয়ের সাথে কাজ মেলাও!",
            speechText = "Match your senses to how you explore the world.",
            correctAnswer = "ALL_MATCHED",
            hint = "Ears hear music, Nose smells flowers, Tongue tastes food!",
            explanation = "Five senses master!",
            visualEmoji = "👃",
            matchPairs = listOf("Ear 👂" to "Hear Music 🎵", "Nose 👃" to "Smell Rose 🌹", "Tongue 👅" to "Taste Mango 🥭")
        ),
        LearningActivity(
            id = "sci_3", worldId = WorldId.SCIENCE, title = "Human Body: Pumping Heart",
            topicTag = "Human Body", type = ActivityType.MULTIPLE_CHOICE, difficulty = DifficultyLevel.EXPLORER,
            prompt = "Which organ pumps blood around your whole body all day and night?",
            promptBn = "শরীরের কোন অঙ্গ সারা শরীরে রক্ত পাম্প করে?",
            speechText = "Which organ pumps blood inside your chest?",
            options = listOf("Heart ❤️", "Tooth 🦷", "Knee 🦵", "Hair 💇"), correctAnswer = "Heart ❤️",
            hint = "Put your hand on your chest to feel it beat: lub-dub!",
            explanation = "Your amazing heart beats around 100,000 times a day!",
            visualEmoji = "❤️"
        ),
        LearningActivity(
            id = "sci_4", worldId = WorldId.SCIENCE, title = "Human Body: Thinking Brain",
            topicTag = "Human Body", type = ActivityType.MULTIPLE_CHOICE, difficulty = DifficultyLevel.BEGINNER,
            prompt = "Which part inside your head helps you think, dream, and solve puzzles?",
            promptBn = "মাথার ভেতরের কোন অংশ আমাদের চিন্তা করতে সাহায্য করে?",
            speechText = "Which part inside your head helps you think and learn?",
            options = listOf("Brain 🧠", "Elbow 💪", "Foot 🦶", "Nail 💅"), correctAnswer = "Brain 🧠",
            hint = "Wearing a helmet keeps it safe when riding a bike!",
            explanation = "Your brain grows stronger every time you learn!",
            visualEmoji = "🧠"
        ),
        LearningActivity(
            id = "sci_5", worldId = WorldId.SCIENCE, title = "Animal Sounds Listen",
            topicTag = "Animals", type = ActivityType.AUDIO_LISTEN, difficulty = DifficultyLevel.BEGINNER,
            prompt = "Listen to the clue! Which animal says 'Moo' and gives us milk?",
            promptBn = "কোন প্রাণী 'হাম্বা' বা 'Moo' ডাকে?",
            speechText = "Moo! Moo! I eat green grass and give healthy milk. Who am I?",
            options = listOf("Cow 🐄", "Lion 🦁", "Duck 🦆", "Snake 🐍"), correctAnswer = "Cow 🐄",
            hint = "It lives on a farm!",
            explanation = "Yes! A friendly Cow says Moo!",
            visualEmoji = "🐄", isVocabulary = true
        ),
        LearningActivity(
            id = "sci_6", worldId = WorldId.SCIENCE, title = "Birds & Insects Sort",
            topicTag = "Birds & Insects", type = ActivityType.DRAG_SORT, difficulty = DifficultyLevel.BEGINNER,
            prompt = "Sort these flying friends into Birds (feathers) or Insects (6 legs)!",
            promptBn = "পাখি আর পোকামাকড় আলাদা করো!",
            speechText = "Sort the animals into Birds and Insects.",
            correctAnswer = "ALL_SORTED",
            hint = "Owls and Ducks have feathers. Bees and Butterflies are insects!",
            explanation = "You know how to tell birds and insects apart!",
            visualEmoji = "🦋",
            buckets = listOf("Birds", "Insects"),
            dragItems = listOf("Owl 🦉" to "Birds", "Honeybee 🐝" to "Insects", "Duck 🦆" to "Birds", "Butterfly 🦋" to "Insects")
        ),
        LearningActivity(
            id = "sci_7", worldId = WorldId.SCIENCE, title = "What Do Plants Need?",
            topicTag = "Plants", type = ActivityType.MULTIPLE_CHOICE, difficulty = DifficultyLevel.BEGINNER,
            prompt = "What three main things do green plants need to grow healthy and tall?",
            promptBn = "গাছ বড় হতে কী কী দরকার?",
            speechText = "What do plants need to grow healthy and tall?",
            options = listOf("Sunlight, water, and air", "Pizza, toys, and TV", "Ice cream and snow", "Shoes and hats"),
            correctAnswer = "Sunlight, water, and air",
            hint = "Put a plant near a window and give it water!",
            explanation = "Plants use sunlight, water, and air to make their own food!",
            visualEmoji = "🌱"
        ),
        LearningActivity(
            id = "sci_8", worldId = WorldId.SCIENCE, title = "Solar System: Red Planet",
            topicTag = "Solar System", type = ActivityType.MULTIPLE_CHOICE, difficulty = DifficultyLevel.EXPLORER,
            prompt = "Which planet is famous for its rusty red dust and is called the Red Planet?",
            promptBn = "সৌরজগতের 'লাল গ্রহ' কোনটি?",
            speechText = "Which planet is called the Red Planet?",
            options = listOf("Mars 🔴", "Earth 🌍", "Neptune 🔵", "Saturn 🪐"), correctAnswer = "Mars 🔴",
            hint = "Its name starts with M and space rovers explore it!",
            explanation = "Mars is the Red Planet! Scientists send robot rovers there!",
            visualEmoji = "🚀"
        ),
        LearningActivity(
            id = "sci_9", worldId = WorldId.SCIENCE, title = "Solar System: Ringed Giant",
            topicTag = "Solar System", type = ActivityType.PICTURE_MATCH, difficulty = DifficultyLevel.EXPLORER,
            prompt = "Which giant planet wears bright icy rings around its middle? 🪐",
            promptBn = "কোন গ্রহের চারপাশে সুন্দর বলয় বা রিং আছে?",
            speechText = "Which planet is famous for its huge bright rings?",
            options = listOf("Saturn", "Mercury", "Earth", "Venus"), correctAnswer = "Saturn",
            hint = "Look at the ringed emoji 🪐!",
            explanation = "Saturn has dazzling rings made of ice and rocks!",
            visualEmoji = "🪐"
        ),
        LearningActivity(
            id = "sci_10", worldId = WorldId.SCIENCE, title = "Day & Night Science",
            topicTag = "Day and Night", type = ActivityType.MULTIPLE_CHOICE, difficulty = DifficultyLevel.EXPLORER,
            prompt = "Why do we have Day and Night on Earth?",
            promptBn = "পৃথিবীতে দিন আর রাত কেন হয়?",
            speechText = "Why do we have day and night on Earth?",
            options = listOf("Because Earth spins like a top", "Because the Sun turns off", "Because clouds cover space", "Because birds go to sleep"),
            correctAnswer = "Because Earth spins like a top",
            hint = "When our side faces the Sun it is daytime!",
            explanation = "Earth spins around once every 24 hours, giving us day and night!",
            visualEmoji = "🌍"
        ),
        LearningActivity(
            id = "sci_11", worldId = WorldId.SCIENCE, title = "Water Cycle Experiment",
            topicTag = "Water Cycle", type = ActivityType.ANIMATED_SCENE, difficulty = DifficultyLevel.EXPLORER,
            prompt = "Safe Experiment: Sun warms puddle water into vapor, forming clouds. Then what falls down?",
            promptBn = "সূর্যের তাপে পানি বাষ্প হয়ে মেঘ হয়। তারপর কী ঝরে পড়ে?",
            speechText = "The sun warms water into clouds. When clouds get heavy, what falls down?",
            options = listOf("Rain 🌧️", "Sand 🏜️", "Feathers 🪶", "Leaves 🍂"), correctAnswer = "Rain 🌧️",
            hint = "Open your umbrella for this!",
            explanation = "Yes! Evaporation → Clouds → Rain! That is the Water Cycle!",
            visualEmoji = "🌧️"
        ),
        LearningActivity(
            id = "sci_12", worldId = WorldId.SCIENCE, title = "Animal Habitats Match",
            topicTag = "Habitats", type = ActivityType.PAIR_MATCH, difficulty = DifficultyLevel.BEGINNER,
            prompt = "Match each animal to its natural home (habitat)!",
            promptBn = "কোন প্রাণী কোথায় থাকে মেলাও!",
            speechText = "Match each animal to where it lives.",
            correctAnswer = "ALL_MATCHED",
            hint = "Whales swim in the Ocean, Camels walk in the Desert!",
            explanation = "Habitat hero!",
            visualEmoji = "🏕️",
            matchPairs = listOf("Whale 🐋" to "Ocean 🌊", "Camel 🐪" to "Desert 🏜️", "Monkey 🐒" to "Jungle 🌴")
        ),
        LearningActivity(
            id = "sci_13", worldId = WorldId.SCIENCE, title = "Safe Experiment: Sink or Float?",
            topicTag = "Experiments", type = ActivityType.DRAG_SORT, difficulty = DifficultyLevel.EXPLORER,
            prompt = "Drop items in water! Which ones Float and which ones Sink?",
            promptBn = "পানিতে কোন জিনিস ভাসে আর কোনটি ডুবে যায়?",
            speechText = "Predict which items sink and which float in water.",
            correctAnswer = "ALL_SORTED",
            hint = "Heavy rocks and keys sink. Cork and rubber ducks float!",
            explanation = "Brilliant scientist! Lighter objects with trapped air float!",
            visualEmoji = "🛁",
            buckets = listOf("Floats 🎈", "Sinks ⚓"),
            dragItems = listOf("Rubber Duck 🦆" to "Floats 🎈", "Rock 🪨" to "Sinks ⚓", "Leaf 🍃" to "Floats 🎈", "Iron Key 🔑" to "Sinks ⚓")
        ),
        LearningActivity(
            id = "sci_14", worldId = WorldId.SCIENCE, title = "Weather Rainbow",
            topicTag = "Weather", type = ActivityType.MULTIPLE_CHOICE, difficulty = DifficultyLevel.BEGINNER,
            prompt = "When sunlight shines through raindrops in the sky, what colorful arch appears?",
            promptBn = "বৃষ্টির পর রোদ উঠলে আকাশে কী দেখা যায়?",
            speechText = "When sunlight shines through raindrops, what appears in the sky?",
            options = listOf("A Rainbow 🌈", "A Snowman ⛄", "A Volcano 🌋", "An Earthquake"), correctAnswer = "A Rainbow 🌈",
            hint = "It has 7 beautiful colors!",
            explanation = "A Rainbow has Red, Orange, Yellow, Green, Blue, Indigo, and Violet!",
            visualEmoji = "🌈"
        ),
        LearningActivity(
            id = "sci_15", worldId = WorldId.SCIENCE, title = "Love Our Environment",
            topicTag = "Environment", type = ActivityType.MULTIPLE_CHOICE, difficulty = DifficultyLevel.BEGINNER,
            prompt = "Which action helps keep our Earth clean and green?",
            promptBn = "পৃথিবীকে সুন্দর ও পরিচ্ছন্ন রাখতে কী করা উচিত?",
            speechText = "How can we help keep Earth clean and green?",
            options = listOf("Plant trees & recycle", "Throw trash in rivers", "Leave taps running", "Cut down forests"),
            correctAnswer = "Plant trees & recycle",
            hint = "Trees give us fresh oxygen!",
            explanation = "Planting trees and recycling makes Earth smile!",
            visualEmoji = "♻️"
        ),
        LearningActivity(
            id = "sci_16", worldId = WorldId.SCIENCE, title = "Parts of a Plant",
            topicTag = "Plants", type = ActivityType.MULTIPLE_CHOICE, difficulty = DifficultyLevel.EXPLORER,
            prompt = "Which part of a plant grows underground and drinks water from soil?",
            promptBn = "গাছের কোন অংশ মাটির নিচ থেকে পানি শোষণ করে?",
            speechText = "Which part of a plant drinks water underground?",
            options = listOf("Roots", "Petals", "Fruits", "Seeds"), correctAnswer = "Roots",
            hint = "They hold the plant firmly in the dirt!",
            explanation = "Roots act like straws drinking water from the soil!",
            visualEmoji = "🌿"
        ),
        LearningActivity(
            id = "sci_17", worldId = WorldId.SCIENCE, title = "Solar System: Closest Star",
            topicTag = "Solar System", type = ActivityType.MULTIPLE_CHOICE, difficulty = DifficultyLevel.MASTER,
            prompt = "The Sun at the center of our solar system is actually a huge glowing...",
            promptBn = "আমাদের সূর্য আসলে কী?",
            speechText = "What is the Sun at the center of our solar system?",
            options = listOf("Star ⭐", "Moon 🌙", "Comet ☄️", "Cloud ☁️"), correctAnswer = "Star ⭐",
            hint = "It shines its own heat and light!",
            explanation = "Yes! The Sun is our closest star!",
            visualEmoji = "☀️"
        ),
        LearningActivity(
            id = "sci_18", worldId = WorldId.SCIENCE, title = "Safe Experiment: Magnet Magic",
            topicTag = "Experiments", type = ActivityType.MULTIPLE_CHOICE, difficulty = DifficultyLevel.MASTER,
            prompt = "If you hold a magnet over a paperclip and a wooden spoon, what will the magnet pick up?",
            promptBn = "চুম্বক কাঠের চামচ আর লোহার পেপারক্লিপের মধ্যে কোনটি টেনে নেবে?",
            speechText = "What will a magnet attract: a metal paperclip or a wooden spoon?",
            options = listOf("The metal paperclip", "The wooden spoon", "Both of them", "Neither"),
            correctAnswer = "The metal paperclip",
            hint = "Magnets attract metals like iron and steel!",
            explanation = "Spot on! Magnets pull iron and steel paperclips!",
            visualEmoji = "🧲"
        )
    )
}
