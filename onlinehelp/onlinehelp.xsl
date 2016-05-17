<?xml version="1.0"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
  xmlns:doc="http://nwalsh.com/xsl/documentation/1.0"
  xmlns:ng="http://docbook.org/docbook-ng"
  xmlns:db="http://docbook.org/ns/docbook"
  xmlns:exsl="http://exslt.org/common"
  version="1.0"
  exclude-result-prefixes="doc ng db exsl">
  
  	<xsl:param name="l10n.gentext.default.language">de</xsl:param>
  	<xsl:param name="l10n.gentext.language">de</xsl:param>
  	<xsl:param name="javahelp.encoding">UTF-8</xsl:param>
  
  	<xsl:include href="docbook-xsl-1.75.2/javahelp/javahelp.xsl"/>
</xsl:stylesheet>