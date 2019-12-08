package com.synergywebdesigners.nima;

/**
 * Created by PC on 29-Dec-16.
 */

public class Config {
    //URL to our login.php file
    public static final String LOGIN_URL = "http://www.synergywebdesigners.com/synergywebdesigners.com/ashish/nima/login.php";

    //Keys for email and password as defined in our $_POST['key'] in login.php
    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASSWORD = "password";

    //If server response is equal to this that means login is successful
    public static final String LOGIN_SUCCESS = "success";

    //Keys for Sharedpreferences
    //This would be the name of our shared preferences
    public static final String SHARED_PREF_NAME = "myloginapp";

    //This would be used to store the email of current logged in user
    public static final String USERNAME_SHARED_PREF = "username";

    //We will use this to store the boolean in sharedpreference to track user is loggedin or not
    public static final String LOGGEDIN_SHARED_PREF = "loggedin";
    //showing Profile Detail
    public static final String DATA_URL = "http://www.synergywebdesigners.com/synergywebdesigners.com/ashish/nima/profile.php?username=";
    public static final String KEY_NAME = "username";
    public static final String KEY_UNAME = "m_name";
    public static final String KEY_ORG = "m_org";
    public static final String KEY_TRADE = "m_trade";
    public static final String KEY_DATE = "m_date";
    public static final String KEY_OWNERSHIP = "m_ownership";
    public static final String KEY_ADD = "m_add";
    public static final String KEY_TELE = "m_tele";
    public static final String KEY_MOB = "m_mob";
    public static final String KEY_OFMAIL = "m_ofmail";
    public static final String KEY_PERMAIL = "m_permail";
    public static final String KEY_WEB = "m_web";
    public static final String KEY_BRANCHE = "m_branche";
    public static final String KEY_IATA = "m_iata";
    public static final String KEY_TURNOVER = "m_turnover";
    public static final String KEY_PAN = "m_pan";
    public static final String KEY_TAXNO = "m_taxno";
    public static final String KEY_ORGID = "m_orgid";
    public static final String KEY_WD = "m_wd";
    public static final String KEY_FDESTINATION = "m_first_destination";
    public static final String KEY_SDESTINATION = "m_second_destination";
    public static final String KEY_GROUP = "m_no_group";
    public static final String KEY_TRADEW = "m_tradew";
    public static final String KEY_FAUTHORIZED = "m_fauthorized";
    public static final String KEY_SAUTHORIZED = "m_sauth";
    public static final String KEY_LINKEDIN = "m_linkedin";
    public static final String KEY_REFERENCE = "m_reference";
    public static final String KEY_PICTURE = "picture";
    public static final String JSON_ARRAY = "result";

    //show for slide show
    public static final String DATA_URL_NEWS = "http://www.synergywebdesigners.com/synergywebdesigners.com/ashish/nima/slidenews.php";
    public static final String KEY_TITLE = "title";
    public static final String KEY_NEWS = "message";
    public static final String KEY_CREATED = "created";
    public static final String JSON_ARRAY_NEWS = "result";


    //chating activity
    public static final String URL_ADD="http://synergywebdesigners.com/synergywebdesigners.com/ashish/nima/createmessage.php";
    public static final String URL_GET_ALL = "http://synergywebdesigners.com/synergywebdesigners.com/ashish/nima/showchat.php";
    public static final String KEY_USER = "username";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_ORGNISATION = "org";
    public static final String KEY_CREATE = "created";
    //JSON Tags
    public static final String TAG_JSON_ARRAY="result";
    public static final String TAG_UNAME = "username";
    public static final String TAG_MESSAGE = "message";
    public static final String TAG_ORG = "org";
    public static final String TAG_CREATED = "created";
    //showing Resources
    public static final String TAG_JSON_RESULT = "result";
    public static final String TAG_COUNTRY = "country";
    public static final String URL_GET_ALL_RESOURCES = "http://synergywebdesigners.com/synergywebdesigners.com/ashish/nima/show-resources.php";
    //Showing subCountry
    public static final String TAG_JSON_RESULT_SUB = "result";
    public static final String TAG_SUB_COUNTRY = "state";
    public static final String URL_GET_ALL_RESOURCES_SUB = "http://www.synergywebdesigners.com/synergywebdesigners.com/ashish/nima/sub-resources.php?country=";
    //Showing Tourism
    public static final String TAG_JSON_TOURISM="result";
    public static final String TAG_TITLE ="title";
    public static final String TAG_DETAIL="detail";
    public static final String TAG_IMAGE = "picture";
    public static final String URL_GET_ALL_TOURISM="http://www.synergywebdesigners.com/synergywebdesigners.com/ashish/nima/tourism.php?state=";
    //showing Noticeboard
    public static final String TAG_JSON_NOTICE="result";
    public static final String TAG_NOTICE_TITLE="title";
    public static final String TAG_PICTURE="picture";
    public static final String TAG_NOTICE = "detail";
    public static final String TAG_DAY = "day";
    public static final String URL_GET_ALL_NOTICE="http://synergywebdesigners.com/synergywebdesigners.com/ashish/nima/noticeboard.php";
    //showing Hotel Detail
    public static final String TAG_JSON_HOTEL="result";
    public static final String TAG_HOTEL="name";
    public static final String TAG_HOTEL_DETAIL="description";
    public static final String TAG_HOTEL_PICTURE="picture";
    public static final String URL_GET_ALL_HOTEL="http://www.synergywebdesigners.com/synergywebdesigners.com/ashish/nima/hotel.php?hotel=";
    //showing Event Detail
    public static final String TAG_JSON_EVENT="result";
    public static final String TAG_EVENT_DESC="description";
    public static final String URL_GET_ALL_EVENT="http://synergywebdesigners.com/synergywebdesigners.com/ashish/nima/event.php";
    //showing Airline Detail
    public static final String TAG_JSON_AIRLINE="result";
    public static final String TAG_AIRLINE="name";
    public static final String TAG_AIRLINE_DETAIL="description";
    public static final String TAG_AIRLINE_PICTURE="picture";
    public static final String URL_GET_ALL_AIRLINE="http://synergywebdesigners.com/synergywebdesigners.com/ashish/nima/airline.php?airline=";
    //Advertisment Section
    public static final String TAG_JSON_ADVERTIMENT="result";
    public static final String TAG_MICE="mice";
    public static final String TAG_DMC="dmc";
    public static final String TAG_HOTEL_AD="hotel";
    public static final String URL_GET_ADVERTISMENT="http://synergywebdesigners.com/synergywebdesigners.com/ashish/nima/advertisment.php";

    //showing News Advertisment
    public static final String TAG_JSON_NEWSADV="result";
    public static final String TAG_NEWSADV_PICTURE="picture";
    public static final String URL_GET_NEWSADV="http://synergywebdesigners.com/synergywebdesigners.com/ashish/nima/newsadv.php";
    //chating activity
    public static final String URL_ADD_PCHAT="http://synergywebdesigners.com/synergywebdesigners.com/ashish/nima/personalmessage.php";
    public static final String KEY_SENDER_PCHAT = "sender";
    public static final String KEY_RECIVER_PCHAT = "reciver";
    public static final String KEY_MESSAGE_PCHAT = "message";
    public static final String KEY_COMPANY_PCHAT = "cname";
    public static final String KEY_DAY_PCHAT = "day";
    //JSON Tags
    public static final String TAG_JSON_ARRAY_PCHAT="result";
    public static final String URL_GET_ALL_PCHAT = "http://www.synergywebdesigners.com/synergywebdesigners.com/ashish/nima/showpersonalchat.php?reciver=";
    public static final String URL_GET_DUMMY_PCHAT ="&sender=";
    public static final String TAG_SENDER_PCHAT = "sender";
    public static final String TAG_MESSAGE_PCHAT = "message";
    public static final String TAG_CREATED_PCHAT = "created";
}
