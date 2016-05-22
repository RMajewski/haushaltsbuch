#!/bin/sh

# Onlinehilfe generieren
./generate_help.sh

# Verzeichnisanlegen
if [ ! -d ../result/wiki ]
then
  mkdir ../result/wiki
else
  rm ../result/wiki/*
fi

# Text-Dateien erzeugen
cd ../resource/help
for datei in ch*.html
do
  # Erstelle eine Text-Dateie aus den HTML-Dateie
  result=../../result/wiki/`basename $datei .html`.txt
  lynx -dump $datei > ../../result/wiki/$result
  
  # Die ersten 3 Zeilen löschen
  sed -i 1D $result
  sed -i 1D $result
  sed -i 1D $result
  
  # Leerzeichen am Anfang der Zeilen löschen
  sed -i 's/^[ \t]*//' $result
  
  # Kapitel und Kapitel-Nummer löschen und stattdessen # einfügen
  sed -i 's/Kapitel[ .0-9]*/#/g' $result
done

