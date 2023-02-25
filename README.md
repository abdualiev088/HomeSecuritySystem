![Frame 2](https://user-images.githubusercontent.com/61827807/216751412-5c3d48c0-16e3-4a0c-8ff8-cdcae6987250.svg)

# HomeSecuritySystem

## Overview
This is my first android app development porject. The idea was to create something like smart door bell with AI and show all people’s face on app. PC’s camera is always running, if any face detected PC will take a picture of him/his face. Here an app is used to show informations about people. The project is for subject session. Me and my freind working on it together. I personaly wrote an [Android] app and [Api] Backend, he [ML] face detection and [Arduino] parts for door opening imitation with servo motor rotating and lighting a green light. 

## How is it work step by step
[PC camera] will take a picture if person face detected here we use for detection [OpenAi]. And it will send through an api [Python Flask] to SQLite with parameters(image[binary], date[String], time[String], isFamiliar[Binaru] by default it is = False). From an app you can see in recyclerView all people who came near to your home’’ and dashboard that represent count of Familiar and UnFamiliar people. isFamiliar field here represented as Switch button with Geen or Red colors with values True or False respectively. By pressing Yellow key button [Arduino] a servo motor will rotate to 45 angle and green light will work.

![Frame 5](https://user-images.githubusercontent.com/61827807/216751921-27cba9e6-4eb2-4823-9e86-f1949c266db2.png)
![Frame 3](https://user-images.githubusercontent.com/61827807/216751824-9724a3d2-ffe0-4b2f-9eb5-0edd8afabb83.png)
![Frame 4](https://user-images.githubusercontent.com/61827807/216751825-a973f58b-15c7-4533-8e14-448d02a5c5b9.png)

## All packages and dependencies used:
1. ViewModel
2. Room
3. OkHttp
4. Retrofit
5. Gson
6. Flask
7. OpenAI

## Devices:
1. PC
2. Android Mobile Phone
3. Arduino
4. Servo Motor
5. Wifi modul
6. Green and Red lights
7. WebCam

## Database:
1. SQLite

<img width="1280" alt="Снимок экрана 2023-01-22 в 22 03 53" src="https://user-images.githubusercontent.com/61827807/216614216-44920c72-c393-4888-b644-814936ae5e9b.png">
