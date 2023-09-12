## about
This is an app that just encrypts strings.
Manage your account in any format you want.
Therefore, it is possible to have multiple accounts for one service.

We were also particular about the design.
It is difficult for people around you to spy on you.

Manage your encrypted strings in your favorite cloud.
Conversely, be careful not to become complacent before putting it in the cloud.

This app does not save any data on your smartphone or within the app.
The security level is therefore that of the cloud you use.

## screen shot
|1|2|3|
|---|---|---|
|Text data search screen before encryption|Text data display screen before encryption|Text data display screen after encryption|
|![Screenshot_20230912-110332](https://github.com/8suzuran8/tool-secret/assets/95464364/3bc4b4b3-c833-4db2-ac05-07274dad3294)|![Screenshot_20230912-110341](https://github.com/8suzuran8/tool-secret/assets/95464364/7ed5e52e-c0f3-40a8-9024-d1e968cbb6a2)|![Screenshot_20230912-110348](https://github.com/8suzuran8/tool-secret/assets/95464364/4812b340-a70e-4c53-bf44-2c00fb56cdbc)|

### example
grouping
![Screenshot_20230912-110107](https://github.com/8suzuran8/tool-secret/assets/95464364/6c2ebf58-f9de-4431-8c9a-5021db12d135)

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

## ignore updates with git
git update-index --assume-unchanged app/src/main/res/values/strings.xml
