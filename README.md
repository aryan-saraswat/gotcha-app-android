# Gotcha!
This is an app created by Dennis Michel and me, Aryan Saraswat, both students at the University of Duisburg-Essen. The idea was to implement NLP concepts in an app, so it could make use of a phone's sensors to deliver some functionality. The app tracks your usage of swear language and alerts when you do so.<br/>We used Android Studio as our framework for the native app, as it helped us use Google's SpeechRecognizer API for speech-to-text.

# What you need
To run the app, you need Android Studio with a minimum of API level 8 running, because SpeechRecognizer was introduced on this level. This is not such a hard requirement to fulfil because all Android apps nowadays are developed with Android 9.0 as standard (which is already level 28).

# Cloning the app to your computer
Navigate on your computer using Terminal to a directory where you want to store the repository. After you're there, run the command `git clone <link>`, where <link> is the link to the master branch on this repository.<br/>
After doing so, open Android Studio on your computer, and on the top-left, in the File menu, select "open", navigate to the project you just cloned, and click on it. This should open all the code on your computer on Android Studio.

# How to use the app
The app comes with basic instructions and a tutorial screen, which covers all you need to know about it. There's a main screen with a red button that you press to make a recording, and the text appears in a textbox after it detects that you're done speaking.

<img width="180" height="400" src="https://user-images.githubusercontent.com/73822940/112623313-7d7d4900-8e2c-11eb-9602-0945d25106bb.png">

After it obtains the text from your speech, it performs a search through a dictionary (that you can optionally fill with your own words), and if there are any matches, will count it as a swear word. When it detects a match with a word in the dictionary, it can play a "Gotcha!" sound and/or vibrate the phone (changeable in the settings).

# Possible issues
Since SpeechRecognizer censors actual swear words, some words could be falsely matched with the dictionary, i.e, even if you don't have such a word entered in your dictionary, the app would still detect it as a match and play the "Gotcha!" sound and/or vibrate the phone.

# Improvements to be made
1. Use Google's Cloud API instead of the SpeechRecognizer class
2. Make a statistics page
3. Include support for more languages
