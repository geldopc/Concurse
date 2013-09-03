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

import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;



public class DateUtil {
	
	public static final int QUANTIDADE_DIAS_SEMANA = 7;
	public static final int QUANTIDADE_MESES_ANO = 12;
	
	/**
	 * Retorna a diferencia em dias entre a data inicial e final informadas.
	 * @param dataFim - Data final
	 * @param dataIni - Data Inicial
	 * @return A diferencas em dias das datas informadas.
	 */
	public static long diferencaDias(Date dataFim, Date dataIni) {
		return Math.round((dataFim.getTime() - dataIni.getTime()) / (1000.*60*60*24));
	}
	
	/**
	 * @return A data atual com as horas modificadas para '00:00:00'
	 */
	public static Date getBeginningOfToday() {
		return getBeginningOfDay(new Date());
	}
	
	/**
	 * @return A data atual com as horas modificadas para '23:59:59'
	 */
	public static Date getEndOfToday() {
		return getEndOfDay(new Date());
	}
	
	/**
	 * Metodo que recebe uma data e retorna essa data com as horas modificadas para
	 * '23:59:59'
	 * @param date
	 * @return
	 */
	public static Date getEndOfDay(Date date) {
		if (date == null) {
			return null;
		}
		Calendar dt = new GregorianCalendar();
		dt.setTime(date);
		dt.set(Calendar.HOUR_OF_DAY, 23);
		dt.set(Calendar.MINUTE, 59);
		dt.set(Calendar.SECOND, 59);
		return dt.getTime();		
	}
	
	/**
	 * Metodo que recebe uma data e retorna essa data com as horas modificadas para
	 * '00:00:00'
	 * @param date
	 * @return
	 */
	public static Date getBeginningOfDay(Date date) {
		if (date == null) {
            return null;
        }
        Calendar dt = new GregorianCalendar();
        dt.setTime(date);
        dt.set(Calendar.HOUR_OF_DAY, 0);
        dt.set(Calendar.MINUTE, 0);
        dt.set(Calendar.SECOND, 0);
        dt.set(Calendar.MILLISECOND, 0);
        return dt.getTime();        
    }    
	
	/**
	 * Retorna a data atual no formato informado.
	 * @param formato - Formato que deseja receber a data.
	 * @return Data atual.
	 */
	public static String getDataAtual(String formato) {
		SimpleDateFormat fm = new SimpleDateFormat(formato);
		String data = null;
		try {
			data = fm.format(new Date());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return data;
	}
	
	/**
	 * Testa se a data informada está entre a data inicio e a data fim
	 * @param data - Data que deseja testar se está no intervalo
	 * @param dataInicio - Data inicio do intervalo
	 * @param dataFim - Data fim do intervalo
	 * @return  Verdadeiro se a data estiver no intervalo / 
	 * 			Falso se a data não estiver no intervalo
	 */
	public static Boolean isBetweenDates(Date data, Date dataInicio, Date dataFim){
		return (data.equals(dataInicio) || (data.after(dataInicio) && data.before(dataFim)) || data.equals(dataFim) );
	} 
	
	/**
	 * Testa se a hora informada está entre a hora inicio e a hora fim
	 * @param hora - Hora que deseja testar se está no intervalo
	 * @param horaInicio - Hora inicio do intervalo
	 * @param horaFim - Hora fim do intervalo
	 * @return  Verdadeiro se a hora estiver no intervalo / 
	 * 			Falso se a hora não estiver no intervalo
	 */
	public static Boolean isBetweenHours(Time hora, Time horaInicio, Time horaFim){
		return (hora.equals(horaInicio) || (hora.after(horaInicio) && hora.before(horaFim)) || hora.equals(horaFim));
	}
	
	/**
	 * Valida se a hora final está depois da hora inicial
	 * @param horaInicio
	 * @param horaFim
	 * @return
	 */
	public static boolean validateHour(Time horaInicio, Time horaFim) {
		return horaInicio != null && horaFim != null && horaFim.after(horaInicio);
	}
	
	/**
	 * Metodo onde retorna a data no formato informado
	 * @param data
	 * @param formato
	 * @return
	 */
	public static String getDataFormatada(Date data, String formato){
		if (data == null || formato.length() == 0){
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(formato);
		return sdf.format(data);
	}
	
	public static String getHoraFormatada(Time hora, String formato){
		if(hora == null || formato.length() == 0){
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(formato);
		return sdf.format(hora);
	}
	
	public static String getHoraFormatada(Timestamp hora, String formato){
		if(hora == null || formato.length() == 0){
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(formato);
		return sdf.format(hora);
	}	
	
	public static Calendar dataProximoDiaSemana(Calendar data, int diaSemana) {
		while(data.get(Calendar.DAY_OF_WEEK) != diaSemana){
			data.add(Calendar.DAY_OF_MONTH, 1);
		}
		return data;
	}
	
	public static Date addDia(Date date, int dias) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, dias);
		return calendar.getTime();
	}

	public static boolean existeConflitoPeriodo(Date dateIni1, Date dateFim1, Date dateIni2, Date dateFim2) {
		return DateUtil.isBetweenDates(dateIni1, dateIni2, dateFim2) ||
		DateUtil.isBetweenDates(dateFim1, dateIni2, dateFim2) ||
		DateUtil.isBetweenDates(dateIni2, dateIni1, dateFim1) ||
		DateUtil.isBetweenDates(dateFim2, dateIni1, dateFim1);
	}

	public static boolean isDiasDiferentes(Date date1, Date date2) {
		Calendar c1 = Calendar.getInstance();
		c1.setTime(date1);
		Calendar c2 = Calendar.getInstance();
		c2.setTime(date2);
		return c1.get(Calendar.DAY_OF_MONTH) != c2.get(Calendar.DAY_OF_MONTH) ||
				c1.get(Calendar.YEAR) != c2.get(Calendar.YEAR);
	}
	
	public static Calendar dataProximoMes(Calendar data, int mes) {
		int diferenca = (mes - data.get(Calendar.MONTH));
		if (diferenca < 0) {
			diferenca += 12;
		}
		data.add(Calendar.MONTH, diferenca);
		return data;
	}

	public static boolean isFimDeSemana(Calendar calendar) {
		return calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || 
			   calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY; 
	}

	public static String getMesExtenso(Integer mes) {
		DateFormat dfmt = new SimpleDateFormat("MMM");  
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH, mes-1);
		
		return dfmt.format(calendar.getTime());
	}
	
	public static XMLGregorianCalendar getXMLGregorianCalendarFromDate(Date date) {
		GregorianCalendar gregorianCalendar = new GregorianCalendar();
		gregorianCalendar.setTime(date);
		try {
			XMLGregorianCalendar xmlGrogerianCalendar = DatatypeFactory.newInstance()
				.newXMLGregorianCalendar(gregorianCalendar);
			return xmlGrogerianCalendar;
		} catch (DatatypeConfigurationException e) {
			throw new RuntimeException(e);
		}		
	}
	
	public static Date getDateFromXMLGregorianCalendar(XMLGregorianCalendar xmlGregorianCalendar) {
		return xmlGregorianCalendar.toGregorianCalendar().getTime();
	}
	
	/**
	 * Método que retorna a idade através de uma data
	 * @param data
	 * @return idade
	 */
    public static int getIdade(Date data) {  
        Calendar cData = Calendar.getInstance();  
        Calendar cHoje= Calendar.getInstance();  
        cData.setTime(data);  
        cData.set(Calendar.YEAR, cHoje.get(Calendar.YEAR));  
        int idade = cData.after(cHoje) ? -1 : 0;  
        cData.setTime(data);  
        idade += cHoje.get(Calendar.YEAR) - cData.get(Calendar.YEAR);  
        return idade;  
    } 	
   
    /**
     * 
     * @param data1
     * @param data2
     * @return 
     */
    public static boolean isDataBeforeData2(Date data1,Date data2){
		if(data1 != null && data2 != null &&
				data1.before(data2) ){
			return true;
		}
    	return false;
    }
    
    public static boolean isDataBeforeData2TruncByDay(Date data1, Date data2){
    	return isDataBeforeData2(getBeginningOfDay(data1), getBeginningOfDay(data2));
    }
    
	/**
	 * Adiciona ou decrementa o numero de meses a data informada, para adicionar 
	 * informe numeros positivos para decrementar numeros negativos 
	 * @param data
	 * @param numMeses
	 * @return dataAjustada
	 */
    public static Date addOrDecrementMesesData(Date data, int numMeses){
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(data);
		calendar.add(Calendar.MONTH, numMeses);
		return calendar.getTime();
	}
    
    /**
     * Converte uma string de data para o tipo Date do java, é importante informar
     * o formato da data no campo mascara. 
     * @param dataStr
     * @param mascara
     * @return Date
     */
    public static Date passStringToDate(String dataStr, String mascara){
    	  SimpleDateFormat formatador = new SimpleDateFormat(mascara);  
    	  try {  
    	    return formatador.parse(dataStr);  
    	  } catch(ParseException ex) {   
    	      throw new RuntimeException(ex);  
    	  }
    }
 
    /**
     * Converte uma string de time para o tipo Time do java, é importante informar
     * o formato do time no campo mascara. 
     * @param timeStr
     * @param mascara
     * @return Time
     */    
    public static Time passStringToTime(String timeStr, String mascara){
    	SimpleDateFormat formatador = new SimpleDateFormat(mascara);
    	try{
    		Date dHora = formatador.parse(timeStr);
    		Time time = new Time(dHora.getTime());
    		return time;
  	  } catch(ParseException ex) {   
	      throw new RuntimeException(ex);  
	  }
    }
    
    /**
     * Seta o tempo da data para 00:00:00
     * @param data
     * @return Date
     */
	public static Date tratarData(Date data){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 0, 0, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}
	
	/**
	 * Método que recebe duas data e retorna a quantidade de meses
	 * @param dataInicio
	 * @param dataFim
	 * @return meses
	 */
	public static int quantidadeDeMeses(Calendar dataInicio, Calendar dataFim) {
		int meses = 0;
		if (dataInicio.before(dataFim)) {
			meses += (dataFim.get(Calendar.YEAR) - dataInicio.get(Calendar.YEAR)) * 12;
			meses += dataFim.get(Calendar.MONTH) - dataInicio.get(Calendar.MONTH);
			if (dataFim.get(Calendar.DAY_OF_MONTH) < dataInicio.get(Calendar.DAY_OF_MONTH)) {
				meses -= 1;
			}
		}
		return meses;
	}
	
	/**
	 * Verifica se o dia em questão é sabado ou domingo.
	 * @param data Data esperada para verificar.
	 * @return Em caso de sabado e domingo retorna "false" ou seja não é dia util, demais casos retorna "true" ou seja, é dia util.
	 */
	public static boolean verificaDiaUtil(Date data){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		if ((calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) || 
				(calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)){
			return false;
		}
		return true;
	}
	
	/**
	 * Obtem o próximo dia util.
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static Date getProximoDiaUtil() {
		Calendar dtCiencia = Calendar.getInstance();
		while(!verificaDiaUtil(dtCiencia.getTime())){
			dtCiencia.add(dtCiencia.DAY_OF_MONTH, 1);
		}
		return dtCiencia.getTime();
	}	
	
	public static String diferencaTempo(Date dataFim, Date dataInicio){
		return diferencaTempo(dataFim, dataInicio, false);
	}	
	
	public static String diferencaTempo(Date dataFim, Date dataInicio, boolean incluirMilissegundos){
		if (dataFim == null){
			dataFim = new Date();
		}
		if (dataInicio == null){
			return "Tempo indeterminado";
		}
		long diferencaDias = (dataFim.getTime() - dataInicio.getTime());

		int ms = (int) (diferencaDias % 1000);
		diferencaDias /= 1000;
		int ss = (int) (diferencaDias % 60);
		diferencaDias /= 60;
		int mins = (int) (diferencaDias % 60);
		diferencaDias /= 60;
		int horas = (int) (diferencaDias % 24);
		diferencaDias /= 24;
		int dias = (int) diferencaDias;
		StringBuilder ret = new StringBuilder();
		ret.append(StringUtil.completaZeros(String.valueOf(dias), 4)).append("d ");
		ret.append(StringUtil.completaZeros(String.valueOf(horas), 2)).append("h ");
		ret.append(StringUtil.completaZeros(String.valueOf(mins), 2)).append("m ");
		ret.append(StringUtil.completaZeros(String.valueOf(ss), 2)).append("s ");
		if(incluirMilissegundos){
			ret.append(StringUtil.completaZeros(String.valueOf(ms), 3)).append("ms ");
		}
		return ret.toString();
	}	
}