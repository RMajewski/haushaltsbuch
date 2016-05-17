<?xml version="1.0"?>
<xsl:stylesheet version="1.0"
				xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
				xmlns="http://www.w3.org/TR/Rec-html40">

	<xsl:output method="xml" />
	
	<xsl:template match="/">
		<book xmlns="http://docbook.org/ns/docbook"
			xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
			xsi:schemaLocation="http://docbook.org/ns/docbook http://www.docbook.org/xml/5.0/xsd/docbook.xsd"
			version="5.0">
		
			<info>
				<title>Haushaltsbuch - Onlinehilfe</title>
				<author>
					<personname>
						<firstname>René</firstname>
						<surname>Majewski</surname>
					</personname>
				</author>
				<copyright>
					<year>2016</year>
					<holder>René Majewski</holder>
				</copyright>
			</info>
		
			<xsl:apply-templates />
		</book>
	</xsl:template>
	
	<xsl:template match="chapter">
		<xsl:for-each select="document(@href)">
			<xsl:copy-of select="." />
		</xsl:for-each>
	</xsl:template>
	
 </xsl:stylesheet>