#!/bin/sh

# XML-Datei über alle Kapitel erstellen
java -cp ../lib/saxon.jar com.icl.saxon.StyleSheet chapter/help.xml help_all.xsl > help_all.xml

# Verzeichniss erstellen, wenn es noch nicht existiert
if [ !-d ../resource/help ]
then
  mkdir ../resource/help
else
  rm -R ../resource/help/JavaHelpSearch
  rm ../resource/help/*
fi

cd ../resource/help

# Hilfe-Dateien erstellen
java -cp ../../../saxon.jar com.icl.saxon.StyleSheet ../../onlinehelp/help_all.xml ../../onlinehelp/onlinehelp.xsl

# Index für Volltextsuche erstellen
java -cp ../../../jhall.jar com.sun.java.help.search.Indexer .

cd ../../onlinehelp
