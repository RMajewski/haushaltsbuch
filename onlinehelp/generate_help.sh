#!/bin/sh

cd ../resource
mkdir help
cd help
rm -R JavaHelpSearch
rm *.html
rm *.xml
rm *.jhm
rm *.hs

java -cp ../../../saxon.jar com.icl.saxon.StyleSheet ../../onlinehelp/help.xml ../../onlinehelp/docbook-xsl-1.75.2/javahelp/javahelp.xsl

java -cp ../../../jhall.jar com.sun.java.help.search.Indexer .

cd ../../onlinehelp
