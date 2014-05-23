<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:xs="http://www.w3.org/2001/XMLSchema"
    exclude-result-prefixes="xs"
    version="2.0">
    <xsl:template match="/">
        <html>
            <head><title>Converted Old Card</title></head>
            <body>
                <table border="1" cellpadding="5" width="100%">
                    <tr>
                        <th>Theme</th>
                        <th>Type</th>
                        <th>Country</th>
                        <th>Year</th>
                        <th>Author</th>
                        <th>Valuable</th>
                    </tr>
                    <xsl:for-each select="oldCardList/oldCard">
                        <tr align="center">
                            <td><xsl:value-of select="theme"/></td>
                            <td><xsl:value-of select="type"/></td>
                            <td><xsl:value-of select="cuntry"/></td>
                            <td><xsl:value-of select="year"/></td>
                            <td><xsl:value-of select="author"/></td>
                            <td><xsl:value-of select="valuable"/></td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>