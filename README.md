## screen shot
|1|2|3|
|---|---|---|
|Text data search screen before encryption|Text data display screen before encryption|Text data display screen after encryption|
|![Screenshot_20230901-104533](https://github.com/8suzuran8/tool-secret/assets/95464364/84a9cf5f-4fdb-4c49-8d43-7b55c4e8a829)|![Screenshot_20230901-104542](https://github.com/8suzuran8/tool-secret/assets/95464364/2b75e0a0-ed4b-4298-9b55-4bdc3cc8cb42)|![Screenshot_20230901-104553](https://github.com/8suzuran8/tool-secret/assets/95464364/4d4d1483-3928-46b4-a8e8-d81f340f38b5)
|

## Howto

### How to encrypt
#### step1
Enter account information on screen 2
#### step2
Press the button at the top of screen 2
#### step3
Copy the cipher text on screen 3 and save it wherever you like

### How to decrypt
#### step1
Paste the encrypted text on screen 3.
#### step2
Press the button at the top of screen 3

## change secret key
tool-secret/app/src/main/res/values/strings.xml

<string name="secret_key">0123456789ABCDEF</string>

change "0123456789ABCDEF" to your password

If it is too long, an error will occur.

## change app name
tool-secret/app/src/main/res/values/strings.xml

<string name="app_name">tool-secret</string>

change "tool-secret" to your app name

## change icon
tool-secret/app/src/main/res/mipmap-hdpi/ic_launcher.???

tool-secret/app/src/main/res/mipmap-mdpi/ic_launcher.???

tool-secret/app/src/main/res/mipmap-xhdpi/ic_launcher.???

tool-secret/app/src/main/res/mipmap-xxhdpi/ic_launcher.???

tool-secret/app/src/main/res/mipmap-xxxhdpi/ic_launcher.???



tool-secret/app/src/main/res/mipmap-xxhdpi/ic_launcher_round.???

tool-secret/app/src/main/res/mipmap-xxxhdpi/ic_launcher_round.???

tool-secret/app/src/main/res/mipmap-mdpi/ic_launcher_round.???

tool-secret/app/src/main/res/mipmap-xhdpi/ic_launcher_round.???

tool-secret/app/src/main/res/mipmap-hdpi/ic_launcher_round.???
