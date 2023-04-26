# Mit diesem Programm kann aus einer Liste von Integern in eine Wave-Datei geschrieben werden.
#Zur Hilfe wird hier die "wave"-Bibliothek verwendet.

#Importieren der Bibliotheken.
import wave
import math
import numpy as np

#Öffnen der Datei und Speicherung des rohen Textes in streamtxt Variable.
file1 = open("stream.txt", "r")
streamtxt = file1.read()
file1.close()

#Erstellen einer Liste die aus dem rohen Text eine Liste von Strings der Werte macht.
liste = []
liste = streamtxt.split(",")

#Entfernen des letzten Elements, weil dies leer ist.
ele = liste.pop() 

#Eindämmen der Werte in den zugelassenen Bereich. (0, 256)
x = len(liste)
while x > 0:
    x -= 1
    if int(liste[x]) == 256:
        liste[x] = int(liste[x]) - 1
    elif int(liste[x]) == 0:
        liste[x] = int(liste[x]) + 1
    elif int(liste[x]) == 128:
        liste[x] = 128
    else:
        liste[x] = int(liste[x]) + 0

#Umstülpen der Werte, weil wir mit unsegnierten Integern arbeiten.
x = len(liste) 
while x > 0:
    x -= 1
    if int(liste[x]) < 128:
        liste[x] = int(liste[x]) + 128
    elif int(liste[x]) >= 128:
        liste[x] = int(liste[x]) - 128

#Konvertieren der Liste in ein Byte-Array.
bytearr = bytearray(liste)

#Öffnen der wav-Datei, um die Parameter zu übernehmen.
file1 = wave.open("1.wav", "rb")
rf = file1.readframes(file1.getnframes())
bytearr = bytearr + rf
gc = file1.getnchannels()
rsw = file1.getsampwidth()
rfr = 10000
file1.close()

#Öffnen der wav-Datei um die Bytes des Byte-Arrays zu schreiben.
file1 = wave.open("1.wav", "wb")
file1.setnchannels(gc)
file1.setsampwidth(rsw)
file1.setframerate(rfr)
print("schreibe...")
file1.writeframes(bytearr)

print("fertig!")
