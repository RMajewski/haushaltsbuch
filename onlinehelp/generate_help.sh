#!/bin/sh

java -cp ../lib/saxon.jar com.icl.saxon.StyleSheet chapter/help.xml help_all.xsl > help_all.xml

cd ../resource
mkdir help
cd help
rm -R JavaHelpSearch
rm *.html
rm *.xml
rm *.jhm
rm *.hs

java -cp ../../../saxon.jar com.icl.saxon.StyleSheet ../../onlinehelp/help_all.xml ../../onlinehelp/onlinehelp.xsl

java -cp ../../../jhall.jar com.sun.java.help.search.Indexer .

cd ../../onlinehelp
