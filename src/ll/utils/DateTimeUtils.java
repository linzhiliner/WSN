package ll.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateTimeUtils
{
	/**
	 * 获取当前时间毫秒数（以1970开始计算）
	 * 
	 * @return
	 */
	public static long getMilliSecond()
	{
		return new Date().getTime();
	}

	/**
	 * 返回yyyyMMddHHmmss字符串
	 * 
	 * @return
	 */
	public static String getTimeStamp()
	{
		return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	}

	/**
	 * 时间格式化（DateToString）
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
	 * 时间格式化（String2Date）
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
	 * 获取距离今天几天的日期
	 * 
	 * @param disTodayDays
	 *            距离今天的天数，正数往后推,负数往前移
	 * @return 几天后的日期
	 */
	public static Date getDisTodayDate(int disTodayDays)
	{
		Date date = new Date();// 取时间
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, disTodayDays);// 整数往后推,负数往前移动
		date = calendar.getTime(); // 这个时间就是日期往后推一天的结果
		return date;
	}

	public enum EnumDateFmt
	{
		yyyyMMddHHmmss, yyyyMMddHHmm, yyyyMMdd, HHmmss, MMdd, yyMMddHHmmss
	}

}
