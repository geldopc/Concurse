/*
 IBPM - Ferramenta de produtividade Java
 Copyright (c) 1986-2009 Infox Tecnologia da Informação Ltda.

 Este programa é software livre; você pode redistribuí-lo e/ou modificá-lo 
 sob os termos da GNU GENERAL PUBLIC LICENSE (GPL) conforme publicada pela 
 Free Software Foundation; versão 2 da Licença.
 Este programa é distribuído na expectativa de que seja útil, porém, SEM 
 NENHUMA GARANTIA; nem mesmo a garantia implícita de COMERCIABILIDADE OU 
 ADEQUAÇÃO A UMA FINALIDADE ESPECÍFICA.
 
 Consulte a GNU GPL para mais detalhes.
 Você deve ter recebido uma cópia da GNU GPL junto com este programa; se não, 
 veja em http://www.gnu.org/licenses/   
*/
package br.com.concurse.util;

import java.text.ParseException;
import java.util.Collection;


public final class StringUtil {
	
	private StringUtil() { }
	
    private static final String FOREIGN_CHARS = 
    	"áÁéÉíÍóÓúÚàÀèÈìÌòÒùÙâÂêÊîÎôÔûÛäÄëËïÏöÖüÜãÃõÕçÇñÑ";
    
    private static final String US_CHARS =
        "aAeEiIoOuUaAeEiIoOuUaAeEiIoOuUaAeEiIoOuUaAoOcCnN";
    
    private static String[][] FOREIGN_CHARS_HTML_MATRIX = {
		{ "á", "&aacute;" }, { "Á", "&Aacute;" }, { "é", "&eacute;" },
		{ "É", "&Eacute;" }, { "í", "&iacute;" }, { "Í", "&Iacute;" },
		{ "ó", "&oacute;" }, { "Ó", "&Oacute;" }, { "ú", "&uacute;" },
		{ "Ú", "&Uacute;" }, { "à", "&agrave;" }, { "À", "&Agrave;" },
		{ "è", "&egrave;" }, { "È", "&Egrave;" }, { "ì", "&igrave;" },
		{ "Ì", "&Igrave;" }, { "ò", "&ograve;" }, { "Ò", "&Ograve;" },
		{ "ù", "&ugrave;" }, { "Ù", "&Ugrave;" }, { "â", "&acirc;" },
		{ "Â", "&Acirc;" }, { "ê", "&ecirc;" }, { "Ê", "&Ecirc;" },
		{ "î", "&icirc;" }, { "Î", "&Icirc;" }, { "ô", "&ocirc;" },
		{ "Ô", "&Ocirc;" }, { "û", "&ucirc;" }, { "Û", "&Ucirc;" },
		{ "ä", "&auml;" }, { "Ä", "&Auml;" }, { "ë", "&euml;" },
		{ "Ë", "&Euml;" }, { "ï", "&iuml;" }, { "Ï", "&Iuml;" },
		{ "ö", "&ouml;" }, { "Ö", "&Ouml;" }, { "ü", "&uuml;" },
		{ "Ü", "&Uuml;" }, { "ã", "&atilde;" }, { "Ã", "&Atilde;" },
		{ "õ", "&otilde;" }, { "Õ", "&Otilde;" }, { "ç", "&ccedil;" },
		{ "Ç", "&Ccedil;" }, { "ñ", "&ntilde;" }, { "Ñ", "&Ntilde;" }  	
    };

	
	public static String changeChar(String text, char c1, String c2) {
		StringBuffer aux = new StringBuffer();
		for (int i=0; i < text.length(); i++) {
			char c = text.charAt(i); 
			if (c == c1) {
				aux.append(c2);
			} else {
				aux.append(c);
			}
		}
		return aux.toString();
	}
	
    public static String changeChars(String text, 
    		String oldChars, String newChars) {
        StringBuffer aux = new StringBuffer();
        char let;
        for (int i = 0; i < text.length(); i++) {
            let = text.charAt(i);
            int pos = oldChars.indexOf(let);
            if (pos == -1) {
                aux.append(let);
            } else if (newChars.length() > pos) {
                aux.append(newChars.charAt(pos));
            }
        }
        return aux.toString();
    }

	public static String piece(String text, String delim, int p) {
		return piece(text, delim, p, p);
	}
	
	public static String piece(String text, String delim, int p1, int p2) {
		if ((text == null) || (text.length() == 0)) { return ""; }
		if ((delim == null) || (delim.length() == 0)) { return text; }
		if (p1 < 1) { p1 = 1; } 
		if (p2 < 0) { p2 = 0; }
		if ((p2 != 0) && (p2 < p1)) { return ""; }
		int piece = 1;
		int ini = 0;
		int pos = 0;
		int fim = text.length();
		pos = text.indexOf(delim);
		while (((piece <= p2) || (p2 == 0)) && (pos > -1)) {
			if ((p2 > 0) && (piece == p2)) {
				fim = pos;
			}
			piece++;
			if (piece == p1) {
				ini = pos + delim.length();
			}
			pos = text.indexOf(delim, pos + delim.length());
		}
		if (piece < p1) { return ""; }
		return text.substring(ini, fim);
	}
	
    public static String replace(String subject, String find, String replace) {
        StringBuffer buf = new StringBuffer();
        int lengthSubject = find.length();
        int posAux = 0;
        int posFind = subject.indexOf(find);

        while (posFind != -1) {
            buf.append(subject.substring(posAux, posFind));
            buf.append(replace);
            posAux = posFind + lengthSubject;
            posFind = subject.indexOf(find, posAux);
        }

        buf.append(subject.substring(posAux));
        return buf.toString();
    }	
	
	
    /**
     * Elimina acentuação do texto
     * @param text
     * @return o texto sem os caracteres acentuados
     */
    public static String getUsAscii(String text) {
        return changeChars(text, FOREIGN_CHARS, US_CHARS);
    }
    
    public static String retiraZerosEsquerda(String numero) {
    	if (numero.length() == 0 || !numero.startsWith("0")) {
    		return numero;
    	}
    	char[] charArray = numero.toCharArray();
    	int posUltimoZero = 0;
    	for (char c : charArray) {
			if (c != '0') {
				break;
			}
			posUltimoZero++;
		}
    	return numero.substring(posUltimoZero);
    }
    
    public static String limparCharsNaoNumericos(String s) {
    	//TODO rever esse nome
    	return s.replaceAll("[^0-9]", "");
    }
    
    public static String replaceQuebraLinha(String texto) {
    	if (texto.length() == 0) {
    		return texto;
    	} else {
    		String saida = texto.replace("\\015","");
    		saida = saida.replace("\\012","");
    		saida = saida.replace("\n","");
    		saida = saida.replace("\r","");
    		return saida;
    	}
    }    
	
    public static String removeNaoNumericos(String source) {
    	return source.replaceAll("\\D", "");
    }
    
    public static String removeNaoAlphaNumericos(String source) {
    	return source.replaceAll("\\d", "");
    }    
    
    public static String capitalizeAllWords(String words){
    	StringBuilder out = new StringBuilder();
    	if(words.length() > 0){
    		String[] wordList = words.trim().split(" ");
    		for (int i = 0; i < wordList.length; i++) {
    			if(wordList[i].length() > 1){
				  wordList[i] = wordList[i].substring(0,1).toUpperCase() + wordList[i].substring(1).toLowerCase();
    			} else {
    			  wordList[i] = wordList[i].toUpperCase();
    			}
    			out.append(wordList[i]);
    			out.append(" ");
			}
    	}
    	return out.toString().trim();
    }
    
    public static String completaZeros(String numero, int tamanho) {
    	StringBuilder sb = new StringBuilder();
    	int zerosAdicionar = tamanho - numero.length();
    	while (sb.length() < zerosAdicionar) {
    		sb.append('0');
    	}
    	sb.append(numero);
    	return sb.toString();
    }

    public static String formatNumericString(String string, String mask)
    		throws java.text.ParseException {
    	javax.swing.text.MaskFormatter mf = new javax.swing.text.MaskFormatter(mask);
    	mf.setValueContainsLiteralCharacters(false);
    	return mf.valueToString(string);
    }	
    
    public static String formartCpf(String cpf) {
    	try {
			return formatNumericString(cpf, "###.###.###-##");
		} catch (ParseException e) {
			return null;
		}
    }
    
    public static String formatCnpj(String cnpj) {
    	try {
			return formatNumericString(cnpj, "##.###.###/####-##");
		} catch (ParseException e) {
			return null;
		}
    }
    
    public static String concatList(Collection<Object> list, String delimitador) {
    	StringBuilder sb = new StringBuilder();
    	for (Object object : list) {
			if (sb.length() > 0) {
				sb.append(delimitador);
			}
			sb.append(object);
		}
    	return sb.toString();
    }
    
    public static String toLowerCaseFirstChar(String string) {
    	if (string == null || string.length() == 0) {
    		return string;
    	} else {
    		char[] charArray = string.toCharArray();
    		charArray[0] = Character.toLowerCase(charArray[0]);
    		return String.valueOf(charArray);
    	}
	}
    
    public static String toUpperCaseFirstChar(String string) {
    	if (string == null || string.length() == 0) {
    		return string;
    	} else {
    		char[] charArray = string.toCharArray();
    		for (int i = 0; i < charArray.length; i++) {
    			if (i == 0) {
    				charArray[0] = Character.toUpperCase(charArray[0]);
				}else{
					charArray[i] = Character.toLowerCase(charArray[i]);
				}
			}
    		return String.valueOf(charArray);
    	}
	}
    
    public static String toUpperCaseFirstCharForWord(String string) {
    	if (string == null || string.length() == 0) {
    		return string;
    	} else {
    		StringBuilder palavra = new StringBuilder();
    		String[] words = string.split(" ");
    		char[] charArray = null;
    		for (String word : words) {
    			charArray = word.toCharArray();
    			for (int i = 0; i < charArray.length; i++) {
    				if (i == 0) {
    					charArray[i] = Character.toUpperCase(charArray[0]);
    					palavra.append(charArray[i]);
    				}else{
    					charArray[i] = Character.toLowerCase(charArray[i]);
    					palavra.append(charArray[i]);
    				}
    			}
    			palavra.append(" ");
			}
    		return palavra.toString();
    	}
	}
    
    public static String padRight(String s, int n) {
    	return String.format("%1$-" + n + "s", s);  
    }

    public static String padLeft(String s, int n) {
    	return String.format("%1$#" + n + "s", s);  
    }
    
    /**
     * Converte caracteres especiais de UTF-8 para ISO-8859-1.
     * @param value
     * @return
     * @author Adriano Schmidt
     */
    public static String convertUtf8ToIso88591(String value){
		String convertedString = null;
		if(value != null){
			java.nio.charset.Charset utf8charset = java.nio.charset.Charset.forName("UTF-8");
			java.nio.charset.Charset iso88591charset = java.nio.charset.Charset.forName("ISO-8859-1");
			java.nio.ByteBuffer inputBuffer = java.nio.ByteBuffer.wrap(value.getBytes());
			// decode UTF-8
			java.nio.CharBuffer data = utf8charset.decode(inputBuffer);
			// encode ISO-8559-1
			java.nio.ByteBuffer outputBuffer = iso88591charset.encode(data);
			byte[] outputData = outputBuffer.array();
			convertedString = new String(outputData);
			
			// se a conversao deu errado retorna o valor original
			if (convertedString.contains("?")) {
				return value;
			}
		}
    	
    	return convertedString;
    }
    
    /**
     * Converte letras acentuadas para caracteres especiais.
     * @param text
     * @return
     */
    public static String escapeHtmlForeingChars(String text) {
    	for (String[] matrix : FOREIGN_CHARS_HTML_MATRIX) {
			text = replace(text, matrix[0], matrix[1]);
		}
    	return text;
    }
    
    public static String replaceAspas(String texto){
    	String textReplace = texto.replaceAll("'", "&#39;");
    	textReplace = textReplace.replaceAll("\"", "&quot;");
		return textReplace;
	}
    
    public static boolean soContemNumeros(String texto) {  
    	if(texto == null){  
            return false; 
    	}
        for (char letra : texto.toCharArray()){  
            if(letra < '0' || letra > '9'){
                return false;  
            }
        }
        return true;  
    } 
    
    public static void main(String[] args) {
		System.err.println(soContemNumeros("9o03"));
	}
}