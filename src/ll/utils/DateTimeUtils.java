package ll.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateTimeUtils
{
	/**
	 * ��ȡ��ǰʱ�����������1970��ʼ���㣩
	 * 
	 * @return
	 */
	public static long getMilliSecond()
	{
		return new Date().getTime();
	}

	/**
	 * ����yyyyMMddHHmmss�ַ���
	 * 
	 * @return
	 */
	public static String getTimeStamp()
	{
		return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	}

	/**
	 * ʱ���ʽ����DateToString��
	 * 
	 * @param date
	 * @param enumFmt
	 * @return
	 */
	public static String dateToString(Date date, EnumDateFmt enumFmt)
	{
		switch (enumFmt)
		{
		case yyyyMMddHHmmss:
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
		case yyyyMMddHHmm:
			return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date);
		case yyyyMMdd:
			return new SimpleDateFormat("yyyy-MM-dd").format(date);
		case HHmmss:
			return new SimpleDateFormat("HH:mm:ss").format(date);
		case yyMMddHHmmss:
			return new SimpleDateFormat("yy-MM-dd HH:mm:ss").format(date);
		case MMdd:
			return new SimpleDateFormat("MM-dd").format(date);
		default:
			return "";
		}
	}

	/**
	 * ʱ���ʽ����String2Date��
	 * 
	 * @param dateString
	 * @param enumFmt
	 * @return
	 * @throws ParseException
	 */
	public static Date stringToDate(String dateString, EnumDateFmt enumFmt)
			throws ParseException
	{
		switch (enumFmt)
		{
		case yyyyMMddHHmmss:
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
					.parse(dateString);
		case yyyyMMddHHmm:
			return new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(dateString);
		case yyyyMMdd:
			return new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
		case HHmmss:
			return new SimpleDateFormat("HH:mm:ss").parse(dateString);
		case yyMMddHHmmss:
			return new SimpleDateFormat("yy-MM-dd HH:mm:ss").parse(dateString);
		case MMdd:
			return new SimpleDateFormat("MM-dd").parse(dateString);
		default:
			return null;
		}
	}

	/**
	 * ��ȡ������켸�������
	 * 
	 * @param disTodayDays
	 *            ������������������������,������ǰ��
	 * @return ����������
	 */
	public static Date getDisTodayDate(int disTodayDays)
	{
		Date date = new Date();// ȡʱ��
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, disTodayDays);// ����������,������ǰ�ƶ�
		date = calendar.getTime(); // ���ʱ���������������һ��Ľ��
		return date;
	}

	public enum EnumDateFmt
	{
		yyyyMMddHHmmss, yyyyMMddHHmm, yyyyMMdd, HHmmss, MMdd, yyMMddHHmmss
	}

}
