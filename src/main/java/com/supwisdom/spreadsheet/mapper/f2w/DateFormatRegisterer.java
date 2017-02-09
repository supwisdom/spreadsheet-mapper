package com.supwisdom.spreadsheet.mapper.f2w;

import org.apache.poi.ss.usermodel.CellStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.concurrent.ConcurrentHashMap;

/**
 * register support date format when file date format value read to object field
 * <p>
 * Created by hanwen on 5/3/16.
 */
public class DateFormatRegisterer {

  public static final String FULL_LOCAL_DATE_PATTERN = "yyyy-MM-dd";
  public static final String LOCAL_DATE_WITH_YEAR_MONTH_PATTERN = "yyyy-MM";
  public static final String LOCAL_DATE_WITH_YEAR_PATTERN = "yyyy";
  public static final String FULL_LOCAL_DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
  public static final String LOCAL_DATE_TIME_WITH_HOUR_MIN_PATTERN = "yyyy-MM-dd HH:mm";
  public static final String LOCAL_DATE_TIME_WITH_HOUR_PATTERN = "yyyy-MM-dd HH";
  public static final String ERROR_PATTERN = "error-date-pattern";

  private static final Logger LOGGER = LoggerFactory.getLogger(DateFormatRegisterer.class);

  public static DateFormatRegisterer GLOBAL = new DateFormatRegisterer();

  private ConcurrentHashMap<String, String> DATE_FORMAT_CORRESPONDING = new ConcurrentHashMap<>();

  {
    registerBuildInSupportDateFormat();
  }

  /**
   * register custom date format
   *
   * @param customFormat date format of file value, like: {@link CellStyle#getDataFormatString()}
   * @param dateFormat   date format of {@link SimpleDateFormat} supported
   */
  public void registerFormat(String customFormat, String dateFormat) {
    if (DATE_FORMAT_CORRESPONDING.containsKey(customFormat)) {
      throw new IllegalArgumentException("can not register duplicate custom format, already registered custom format [" + customFormat + "]");
    }
    DATE_FORMAT_CORRESPONDING.put(customFormat, dateFormat);
    LOGGER.info("register format[" + customFormat + "] corresponding as[" + dateFormat + "]");
  }

  /**
   * get custom format corresponding date format
   *
   * @param customFormat date format of file value, like: {@link CellStyle#getDataFormatString()}
   * @return date format of {@link SimpleDateFormat} supported
   */
  public String getCorrespondingFormat(String customFormat) {
    return DATE_FORMAT_CORRESPONDING.get(customFormat);
  }

  private void registerBuildInSupportDateFormat() {
    registerFormat("yyyy", LOCAL_DATE_WITH_YEAR_PATTERN);
    registerFormat("yyyy\\-mm", LOCAL_DATE_WITH_YEAR_MONTH_PATTERN);
    registerFormat("yyyy\\-mm\\-dd", FULL_LOCAL_DATE_PATTERN);
    registerFormat("yyyy\\-mm\\-dd\\ HH", LOCAL_DATE_TIME_WITH_HOUR_PATTERN);
    registerFormat("yyyy\\-mm\\-dd\\ HH:mm", LOCAL_DATE_TIME_WITH_HOUR_MIN_PATTERN);
    registerFormat("yyyy\\-mm\\-dd\\ HH:mm:ss", FULL_LOCAL_DATE_TIME_PATTERN);
  }
}
