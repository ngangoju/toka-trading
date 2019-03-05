/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package toka.common;

public interface DbConstant {
	public static final String USERNAME = "viewId";
	public static final String PASSWORD = "viewName";
	public static final String SELECT_USERS = "from Users";
	public static final String SELECT_SECTORS = "from Sector";
	public static final String SELECT_APPROVER = "from Approver";
	public static final String SELECT_QUESTION_CATEGORY = "from QuestionCategory";
	public static final String SELECT_QUESTION = "from Question";
	public static final String SELECT_RESPONSE = "from Response";
	public static final String USERS = "users";

	public static final String USERCATID = "userCatid";
	public static final String SECTORID = "sectorId";
	public static final String SELECT_USERCATEGORY = "from UserCategory";
	public static final String AVAILABLE = "Available";
	public static final String BOOKED = "Booked";

	public static final String USERID = "userId";
	public static final String DEPID = "departementId";
	public static final String SELECT_DEPARTMENT = "from Departement";
	/* List of Status */
	public static final String INPROGRESS = "In Progress";
	public static final String NOTYET = "Not Yet";
	public static final String APPROVED = "Approved";
	public static final String APPROVEDBYDIRECTOR = "ApprovedByDirector";
	public static final String REJECT = "Rejected";
	public static final String OFFLINE = "offline";
	public static final String ONLINE = "online";
	public static final String ACTIVE = "active";
	public static final String DESACTIVE = "desactive";

	public static final String DEPARTURE = "departure";
	public static final String RETURN = "return";
	/*** Chat ***/
	public static final String CHATFROM = "chatFrom";
	public static final String CHATTO = "chatTo";
	public static final String CHATSTATUS = "status";
	public static final String SELECT_CHAT = "from Chat";
	public static final String RESPONDED = "responded";
	public static final String DESLIKE = "deslike";
	public static final String LIKE = "like";
	public static final String NEW = "New";
	public static final String APPOINTMENT_REQUEST = "APPOINTMENT REQUEST";
	public static final String HOMEURL = "/home.xhtml";
	public static final String PUBLICPATH = "/public/";
	public static final String USERSESSION = "userSession";
	public static final String JAVAFACERESOURCE = "javax.faces.resource";
	public static final String NOTSTARTED = "Not Started";
	public static final String COMPLETED = "Completed";
	public static final String SHORT = "Short";
	public static final String MEDIUM = "Medium";
	public static final String LONG = "Long";
	public static final String PENDING = "pending";
	public static final String ACCEPTED = "acepted";
	public static final String REJECTED = "rejected";
	public static final String SELECT_LISTOFMENU = "from ListOfMenu";
	public static final String SELECT_MENUGROUP = "from MenuGroup";
	public static final String SELECT_PROVINCE = "from Province";
	public static final String country_rw = "Rwanda";
	public static final String SELECT_ACTIVITY = "from Activity";
	public static final String SELECT_TASK = "from Task";
	public static final String SELECT_STRATEGIC_PLAN = "from StrategicPlan";
	public static final String SELECT_BOARD = "from Board";
	public static final String Yes_Option = "yes";
	public static final String Root_Path = "Vfp_Document\\";
	public static final String Next_Option = "next";
	public static final String FILELOCATION = "Vfp_Document\\\\";
	public static final String SELECT_PAYMENTRECORDS = "from PaymentRecords";
	public static final String SELECT_INSTITUTION = "from Institution";
	public static final String pdfFormat = "PDF";
	public static final String PdfforTask = "Pdffortask";
	public static final String exelforTask = "ExcelforTask";
	public static final String taskchart = "Chart";
	public static final String clear="Clearance";

	public static final String DARKBLUE2 = "dark-blue2";
	public static final String GREEN2 = "green2";
	public static final String BLUE2 = "blue2";
	public static final String ORANGE2 = "orange2";
	public static final String RED2 = "red2";
	public static final String PURPLE2 = "purple2";
	public static final String DARKGRAY2 = "dark-gray2";
	public static final String GRAY2 = "gray2";
	public static final String LIGHTGRAY2 = " light-gray2";
	public static final String YELLOW2 = "yellow2";
	public static final String GLYPHICONGLYPHICONASTERISK = "glyphicon glyphicon-asterisk";
	public static final String GLYPHICONGLYPHICONPLUS = "glyphicon glyphicon-plus";
	public static final String GLYPHICONGLYPHICONMINUS = "glyphicon glyphicon-minus";
	public static final String GLYPHICONGLYPHICONEURO = "glyphicon glyphicon-euro";
	public static final String GLYPHICONGLYPHICONCLOUD = "glyphicon glyphicon-cloud";
	public static final String GLYPHICONGLYPHICONENVELOPE = "glyphicon glyphicon-envelope";
	public static final String GLYPHICONGLYPHICONPENCIL = "glyphicon glyphicon-pencil";
	public static final String GLYPHICONGLYPHICONGLASS = "glyphicon glyphicon-glass";
	public static final String GLYPHICONGLYPHICONMUSIC = "glyphicon glyphicon-music";
	public static final String GLYPHICONGLYPHICONSEARCH = "glyphicon glyphicon-search";
	public static final String GLYPHICONGLYPHICONHEART = "glyphicon glyphicon-heart";
	public static final String GLYPHICONGLYPHICONSTAR = "glyphicon glyphicon-star";
	public static final String GLYPHICONGLYPHICONSTAREMPTY = "glyphicon glyphicon-star-empty";
	public static final String GLYPHICONGLYPHICONUSER = "glyphicon glyphicon-user";
	public static final String GLYPHICONGLYPHICONFILM = "glyphicon glyphicon-film";
	public static final String GLYPHICONGLYPHICONTHLARGE = "glyphicon glyphicon-th-large";
	public static final String GLYPHICONGLYPHICONTH = "glyphicon glyphicon-th";
	public static final String GLYPHICONGLYPHICONTHLIST = "glyphicon glyphicon-th-list";
	public static final String GLYPHICONGLYPHICONOK = "glyphicon glyphicon-ok";
	public static final String GLYPHICONGLYPHICONREMOVE = "glyphicon glyphicon-remove";
	public static final String GLYPHICONGLYPHICONZOOMIN = "glyphicon glyphicon-zoom-in";
	public static final String GLYPHICONGLYPHICONZOOMOUT = "glyphicon glyphicon-zoom-out";
	public static final String GLYPHICONGLYPHICONOFF = "glyphicon glyphicon-off";
	public static final String GLYPHICONGLYPHICONSIGNAL = "glyphicon glyphicon-signal";
	public static final String GLYPHICONGLYPHICONCOG = "glyphicon glyphicon-cog";
	public static final String GLYPHICONGLYPHICONTRASH = "glyphicon glyphicon-trash";
	public static final String GLYPHICONGLYPHICONHOME = "glyphicon glyphicon-home";
	public static final String GLYPHICONGLYPHICONFILE = "glyphicon glyphicon-file";
	public static final String GLYPHICONGLYPHICONTIME = "glyphicon glyphicon-time";
	public static final String GLYPHICONGLYPHICONROAD = "glyphicon glyphicon-road";
	public static final String GLYPHICONGLYPHICONDOWNLOADALT = "glyphicon glyphicon-download-alt";
	public static final String GLYPHICONGLYPHICONDOWNLOAD = "glyphicon glyphicon-download";
	public static final String GLYPHICONGLYPHICONUPLOAD = "glyphicon glyphicon-upload";
	public static final String GLYPHICONGLYPHICONINBOX = "glyphicon glyphicon-inbox";
	public static final String SUPER_ADMIN = "Super Admin";
	public static final String INSTITUTE_REP = "Institution Representative";
	public static final String FAILED = "Failed";
	public static final String DONE = "Done";
	public static final String ESCALET = "escalet";
	public static final String Board_opt = "board";
	public static final String Date_opt = "dates";
	public static final String UN_AVAILABLE = "Unavailable";
	public static final String LINK = "http://localhost:8080/";
	public static final String SUPER_VISOR = "Superviser";
	public static final String RESCHDULER = "reschduler";
	public static final String PLAN_ACTIVITY="Planned";
	public static final String NORMAL="Normal";
	public static final String MILESTONE="Vfp";
	public static final String NOTDONE="notdone";
	public static final String DISABLE="disable";
	public static final int endrecord = 6;
	public static final int endCateRecord = 5;
	public static final String DAILY="Daily";
	public static final String WEEKLY="Weekly";
	public static final String MONTHLY="Monthly";
	public static final String YEARLY="Yearly";
	public static final String FILELOCATIONUNIX = "Vfp_Document//";
	public static final int defaultCount=0;
	public static final int incrementCount=1;
}
