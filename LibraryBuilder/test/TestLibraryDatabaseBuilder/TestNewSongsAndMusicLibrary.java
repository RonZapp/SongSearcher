package TestLibraryDatabaseBuilder;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;

import LibraryDatabaseBuilder.MusicLibrary;
import LibraryDatabaseBuilder.Song;
/*
 * Tests many of Song's and MusicLibrary's methods
 * @author RonZapp
 * 
 */
public class TestNewSongsAndMusicLibrary {
	private static final String TRACKID = "TRBDCAB128F92F7EE4";
	private static final String ARTIST = "The Primitives";
	private static final String TITLE = "Never Tell";
	private static final String TAG = "pop";
	
	private static final String[] songs ={"{\"artist\": \"The Primitives\", \"timestamp\": \"2011-09-07 12:34:34.851502\", \"similars\": [[\"TROBUDC128F92F7F0B\", 1], [\"TRWSCCK128F92F7EDB\", 0.98714400000000002], [\"TRDYIPZ128F92F87A9\", 0.082827799999999993], [\"TRYKOWC128F92F87A2\", 0.079636700000000005], [\"TRJSYXF128EF3444D9\", 0.075987899999999997], [\"TRYZMJT128EF3444DE\", 0.075837699999999994], [\"TRZXLRS128F4288E6A\", 0.072281799999999993], [\"TRTGNRC128F92EDE71\", 0.066712199999999999], [\"TRJDGYW128F429D973\", 0.066712199999999999], [\"TROZGGM128F425D99F\", 0.066712199999999999], [\"TRBZHBP128F14690DC\", 0.062322900000000001], [\"TRCAEQI128F425C923\", 0.037453699999999999], [\"TRMQUAR12903CD409F\", 0.037007699999999998], [\"TRFGMMV128F14840F7\", 0.036020799999999999], [\"TRKEGJQ128F4253AB9\", 0.029893300000000001], [\"TRALGMG128F1466853\", 0.022945299999999998], [\"TRKANHB128F425C919\", 0.022945299999999998], [\"TRAZEDI128E0784866\", 0.021940399999999999], [\"TRNAZYY128F932C8CD\", 0.020210800000000001], [\"TRXQASO128F14840F1\", 0.020210800000000001], [\"TRYBFXO12903CFE124\", 0.00151986], [\"TREWDVY128F4253AC5\", 0.00151961], [\"TRMDLTU128F1469075\", 0.0014968], [\"TRYWZKG128F147F5A1\", 0.0014948699999999999], [\"TRQIJNQ128E078E18B\", 0.00145359], [\"TRWRBIK128F93558AB\", 0.0014465400000000001], [\"TRFZMJG128EF35F961\", 0.00141569], [\"TRUMDYF128EF35F95E\", 0.0014156500000000001], [\"TRLXFEB128E0781EA2\", 0.0012387500000000001], [\"TRYHNQL128F932A5DF\", 0.0012160999999999999], [\"TRPZEHB128F9355349\", 0.0012160300000000001], [\"TRTUQHL128F92D7A97\", 0.0012147799999999999], [\"TROBCNF128F92FD652\", 0.0012140499999999999], [\"TRUSAMN128F92FD655\", 0.00121149], [\"TRUWHTT128E078E184\", 0.00120868], [\"TRTBDTC128F147689F\", 0.00120601], [\"TROPTDA128EF34C55E\", 0.00119915], [\"TRUNJHA128F1471784\", 0.0011900599999999999], [\"TRGXILR128EF365F92\", 0.00118961], [\"TRALEZR128F4243AF9\", 0.00118632], [\"TRKKRSY128F4243AF6\", 0.0011842999999999999], [\"TRZILRG128F427E273\", 0.00117724], [\"TRGJBHA128F426C0A1\", 0.0011576900000000001], [\"TRDWTAT128F145BFCD\", 0.00115766], [\"TRQVTAH12903CF0418\", 0.00115434], [\"TRVJDAD128F92C5034\", 0.0011285500000000001], [\"TRFITGW12903CD2045\", 0.00109676], [\"TRNLAZM12903CE5204\", 0.00109676], [\"TRDMKDD128E0794E70\", 0.0010750899999999999], [\"TRDSTZB128F427E41D\", 0.00106287], [\"TRVHCVI12903CEDB05\", 0.0010447099999999999], [\"TRTLDOY128E07821DB\", 0.0010297699999999999], [\"TRUEAEK128F92F06AE\", 0.0010293100000000001], [\"TRHNYUA128F4299177\", 0.0010005400000000001], [\"TRZNIKG128F92CEE61\", 0.00097733299999999993], [\"TRSPNVY128F92E5540\", 0.00097733299999999993], [\"TRWPCMB128F92D1F29\", 0.00097481399999999995], [\"TRREPAN128F92CEAA1\", 0.00097481399999999995], [\"TRDEVMT128F425BEE3\", 0.00097481399999999995], [\"TRUKKLV128F428F4DD\", 0.00096438600000000002], [\"TRDNEKT128F1482548\", 0.00095334800000000002], [\"TRPDNYN128F1473AB2\", 0.00094541799999999995], [\"TRPUDOQ128F9344F23\", 0.00091260600000000005], [\"TRLZQWA12903CB4754\", 0.00090222900000000005], [\"TRDHXDG128F92D7A91\", 0.000899065], [\"TRYDOBY128F92E87F0\", 0.00089596399999999996], [\"TRESTPP128F93031E0\", 0.00089175699999999997], [\"TRSXRAT128F145834D\", 0.00086490299999999998], [\"TRWQKKQ128F1458FAB\", 0.00086364299999999998], [\"TRDKBDU128F4289A9A\", 0.00086156199999999996], [\"TRNVQZF128F423E9F1\", 0.00085831600000000003], [\"TRLBHKW128F14685AD\", 0.00085587200000000003], [\"TRQKEPZ128F92C51B1\", 0.00084997200000000005], [\"TRJDKAU128F1463C20\", 0.00084382100000000002], [\"TRCGQPW128F92C5043\", 0.00084210899999999996], [\"TRFUQMX128F92DFDA2\", 0.00084210899999999996], [\"TRQBRAZ128F14774C9\", 0.00084122400000000001], [\"TRJIIYC128EF33FB6B\", 0.00082545199999999996], [\"TRRCCDJ128F9316403\", 0.00080844300000000005], [\"TRGUWIR12903CB474E\", 0.00080193099999999998], [\"TRHYHCP128F4272D16\", 0.00078937199999999999], [\"TRMSBIM128F145E2D8\", 0.00078117499999999995], [\"TRFSGEG128F14928B9\", 0.00077843699999999999], [\"TRPZBYJ128F14685AE\", 0.00077077199999999997], [\"TRIAPZK128F1458FAD\", 0.00077017700000000004], [\"TRSPHRJ128F1482544\", 0.00076832799999999998], [\"TREEXYY128F146406B\", 0.00076629400000000005], [\"TRNROKF128F145A9FC\", 0.00076342100000000002], [\"TRXQHDV128F145A9F7\", 0.00076342100000000002], [\"TRTGQLN128F14928BA\", 0.00075210700000000001], [\"TRQCCLB128F1469074\", 0.00074993800000000002], [\"TRMIMCI128F427C101\", 0.00074948500000000004], [\"TRAHLWI128F426C29A\", 0.00074508500000000004], [\"TRJOCGW128F4263A32\", 0.00073849600000000005], [\"TRGQNGH128E0792E78\", 0.00073642900000000003], [\"TRGCCYM12903CD2051\", 0.00070106400000000005], [\"TRFYHFU12903CD4614\", 0.00069751999999999998], [\"TRFASKO128F931BE93\", 0.00069726800000000002], [\"TROIYQA128F92EE75E\", 0.00068297500000000005], [\"TRYKKZB128F4264CB7\", 0.00068162599999999996], [\"TRMQRYG128F93138BD\", 0.00064907099999999998], [\"TRCYGOZ12903CC0BAD\", 0.00064907099999999998], [\"TRZGAKR128F4298E19\", 0.00064715400000000005], [\"TRVZBQD128F428EAA9\", 0.00064574999999999995], [\"TROIYSP128F42954B2\", 0.00064574999999999995], [\"TRXEGOO128F146EB4C\", 0.00064153699999999997], [\"TRWDNLT128F429762F\", 0.00063309200000000003], [\"TRFNAQZ128F1485F8C\", 0.00063309200000000003], [\"TRNUPXP12903CE2EDD\", 0.00062516600000000002], [\"TROCUQW12903CF4D4C\", 0.00061361499999999997], [\"TROXALT128EF3622AC\", 0.00060897299999999996], [\"TRBQDAC128F42675D2\", 0.00060896699999999997], [\"TRZSEOS128F9346C8D\", 0.00060896599999999996], [\"TROSBWJ128F145C694\", 0.00060378199999999997], [\"TRNJJRI128F148B1DE\", 0.00057717300000000005], [\"TREELXZ128EF36221D\", 0.00057468199999999997], [\"TRCLHLH12903CBE3E5\", 0.00055343999999999999], [\"TRNPKBW128F4269C20\", 0.00055048], [\"TRLNSYA128F427031E\", 0.00054942399999999996], [\"TRIPRXV12903CF0417\", 0.00054766899999999998], [\"TRKCBFZ128F4266B76\", 0.00053950299999999999], [\"TRZCMTM128F42437C9\", 0.00053814599999999998]], \"tags\": [[\"1980s\", \"100\"], [\"80s\", \"33\"], [\"pop\", \"33\"], [\"alternative\", \"33\"]], \"track_id\": \"TRBDCAB128F92F7EE4\", \"title\": \"Never Tell\"}",
			"{\"artist\": \"Dann Huff\", \"timestamp\": \"2011-07-31 03:39:36.891299\", \"similars\": [[\"TRVPYKC128F4225718\", 1], [\"TRLLDTK128F422571B\", 0.91119899999999998], [\"TRXUTHW128F4264792\", 0.0015405499999999999], [\"TRNPQUD128F426AD18\", 0.00138816], [\"TRPFCDD128F4265741\", 0.00138816], [\"TRMAOBN128F145BA85\", 0.00132044], [\"TRZINUZ128F4277EA1\", 0.00132044], [\"TRITWTC128F4261F87\", 0.00131606], [\"TRUFELJ128F93426AF\", 0.0013055300000000001], [\"TRESBWC128F426592B\", 0.0013055300000000001], [\"TRYALKN128F428B5CF\", 0.00129404], [\"TRIUOOJ128F934A868\", 0.00127198], [\"TREICHE128F934A85D\", 0.00127198], [\"TRNQXSW128F42645FA\", 0.00125493], [\"TRNLVUY128F427EACB\", 0.00123106], [\"TRVNACX128F4265AA9\", 0.00123106], [\"TROJYPN128F42635A7\", 0.00121312], [\"TRYUUPU128F428C65C\", 0.0011622], [\"TRQAGRN128F933BC30\", 0.00116143], [\"TRXTKUH128F932B3F5\", 0.0011611900000000001], [\"TRXZSBU128F931C9B4\", 0.0011611900000000001], [\"TRLFGOL128F428FCD3\", 0.00116106], [\"TRGBTIG128F4285167\", 0.0011240600000000001], [\"TRMCWEE128F930FB14\", 0.00110472], [\"TRZOUPY128F9322FB6\", 0.00110472], [\"TRPJMZO128F92E223D\", 0.0011031400000000001], [\"TROFJLJ128F92E2244\", 0.0011031400000000001], [\"TRAZANH128F42AD286\", 0.0010838499999999999], [\"TRZDDQA128EF367C42\", 0.00100231], [\"TRWXENS128F9331EA1\", 0.00097207700000000001], [\"TRPDCJT128F4241318\", 0.00097078500000000005], [\"TRBRQWL128F93218E0\", 0.00097004700000000003], [\"TRZITEF128F4286BF7\", 0.000967022], [\"TRPVNXS128F932270F\", 0.00096495299999999995], [\"TREWPUF128E078D0F5\", 0.00095626800000000003], [\"TRGSJBY128F9303E66\", 0.00095277299999999997], [\"TRDYLPJ128F428BEE1\", 0.00094798400000000002], [\"TRPGWZQ128F4289981\", 0.00094064400000000003], [\"TRELRYH128F92D2042\", 0.00087988499999999996], [\"TRPBMQO128F9335C36\", 0.00087616699999999996], [\"TRBWMUW128F425468E\", 0.00087152200000000001], [\"TRBCTFW128F931FBE9\", 0.00087152200000000001], [\"TRGWYWC128F422A8B0\", 0.000871439], [\"TRKAECN128F9325206\", 0.00087138799999999996], [\"TRYTZDU128F92F0179\", 0.00087133299999999996], [\"TRXWQIP128F425FF5C\", 0.00085051600000000005], [\"TRVRCCS12903CE72F9\", 0.00084479300000000004], [\"TRQODDV128F92E495B\", 0.00082273100000000005], [\"TRCCULY128F425FF60\", 0.00081450399999999997], [\"TRYSOKO128E0789699\", 0.00080532399999999995], [\"TRHKNEA128F42893DC\", 0.00077808100000000002], [\"TRCHBKI128F4284CEE\", 0.00077808100000000002], [\"TRJFKTC128F148D161\", 0.00075710899999999995], [\"TRBBMRH128F422449F\", 0.00075520100000000005]], \"tags\": [[\"melodic rock\", \"100\"]], \"track_id\": \"TRBECDN128F4225717\", \"title\": \"Exception To The Rule\"}",
			"{\"artist\": \"The Mighty Diamonds\", \"timestamp\": \"2011-07-31 03:40:09.143916\", \"similars\": [[\"TRADDEX128F4250DFC\", 0.88749100000000003], [\"TRERLNX128F4250DF9\", 0.88749100000000003], [\"TROSBIA128F425115E\", 0.82828100000000004], [\"TRCJDBW12903CE70C7\", 0.817523], [\"TRVIHJU12903CBF933\", 0.79400499999999996], [\"TRNLYFH128F4285D5C\", 0.791161], [\"TRRAOAD12903CE7F2C\", 0.79090300000000002], [\"TRBEPRF128F42555FC\", 0.78400999999999998], [\"TRHLBAE128F930D68D\", 0.78171299999999999], [\"TRIHQFE128F9336B3F\", 0.78171299999999999], [\"TRGHYEW128F145AC02\", 0.78126200000000001], [\"TRGBBRV128F428EDD2\", 0.78126200000000001], [\"TRBLWGQ128F932F00A\", 0.78126200000000001], [\"TRPSYPX12903CEB23B\", 0.77529999999999999], [\"TRVGDAZ12903CCE777\", 0.77529999999999999], [\"TRRKMGA12903CEBDEE\", 0.77516099999999999], [\"TRQZNYA128F934149C\", 0.76460899999999998], [\"TRUTCZU128F427BEF5\", 0.76213600000000004], [\"TROZSKR128F42627AA\", 0.76213600000000004], [\"TRGWDPE12903CEB224\", 0.75733099999999998], [\"TRGNUID12903CCE788\", 0.75733099999999998], [\"TRRRWPO128F426F6C4\", 0.73748199999999997], [\"TRUGMRP128F14658CF\", 0.73385500000000004], [\"TRZMHEJ128F931B884\", 0.71964300000000003], [\"TRXLWVH128F146129C\", 0.71271600000000002], [\"TRMHVAY128E0792BF9\", 0.70998899999999998], [\"TROGWRI128F4272C5E\", 0.67545500000000003], [\"TRINEXF128F424A1E4\", 0.67195099999999996], [\"TRBMLVX12903CE2A4B\", 0.66469800000000001], [\"TRIPRSF12903CBA2CA\", 0.63576900000000003], [\"TRUAEUZ128F4250A51\", 0.634239], [\"TROIYVK128F425F775\", 0.62702999999999998], [\"TRSCACN12903CBA2C9\", 0.62422699999999998], [\"TRHLINZ128F93364A2\", 0.62050899999999998], [\"TRIJIVB128F9336555\", 0.62050899999999998], [\"TRDOTDE128F933D94B\", 0.62050899999999998], [\"TRKLFHL128F4233AE0\", 0.62050899999999998], [\"TRMTZAF128F933DC03\", 0.62044900000000003], [\"TRFYLSJ128F426F6C9\", 0.62033300000000002], [\"TRJUOWV128F4251421\", 0.61978699999999998], [\"TRKTWGI128F1453510\", 0.61798900000000001], [\"TRSSTPR128F14538A4\", 0.61406499999999997], [\"TRAAIAN12903CFF16D\", 0.61172499999999996], [\"TRPGVUC128F9338096\", 0.61077199999999998], [\"TRCQUSU128E0792C34\", 0.60423700000000002], [\"TRPNPGX128F147701D\", 0.60423700000000002], [\"TRUICJF128E0792C2E\", 0.579094], [\"TRSXJTK128F4281FAE\", 0.56879100000000005], [\"TRPNYIZ128F421FA56\", 0.53338600000000003], [\"TRCORDN128F4250A5B\", 0.53320999999999996], [\"TRRPDEM128F933804D\", 0.52188000000000001], [\"TRDFZKD128F92F36A7\", 0.51950399999999997], [\"TRPOGMW128C7196767\", 0.507351], [\"TROSUTJ128F92F918F\", 0.50136099999999995], [\"TRSHDRY128F1453E73\", 0.49800499999999998], [\"TRPYFRJ128F92F9CFB\", 0.492759], [\"TRMDKWH128F42513A6\", 0.49163099999999998], [\"TRBECYS12903CE4F71\", 0.48344999999999999], [\"TRPYEUZ128F93065A4\", 0.48344999999999999], [\"TRXMGYT128F92F9CF5\", 0.47409499999999999], [\"TRIUYSB128F4283FC4\", 0.47276499999999999], [\"TRZEDED128F9336BF4\", 0.46692299999999998], [\"TRCGROW128F93092AF\", 0.44915699999999997], [\"TRUYXJJ128F4283FBF\", 0.43816100000000002], [\"TROWJKS128F4277859\", 0.37515999999999999], [\"TRUOYBV128F9313354\", 0.35836899999999999], [\"TRRYQYW128F93162D4\", 0.32420300000000002], [\"TRNWSPO12903CC1505\", 0.30532599999999999], [\"TRKCUVJ12903CD6A41\", 0.30039900000000003], [\"TRBQZAC128F92E0C0C\", 0.24607699999999999], [\"TRYEGQX128F9320001\", 0.176233], [\"TRQXHFZ128F1453540\", 0.016342499999999999], [\"TRKUCCS128F933656A\", 0.016342499999999999], [\"TROTBJG128F930CF65\", 0.016077899999999999], [\"TRFMZBB12903CDDEA8\", 0.015984600000000002], [\"TRIGXLT128F145353D\", 0.015907399999999999], [\"TRPGZMV128F9336566\", 0.015907399999999999], [\"TRTDDQZ12903CAE938\", 0.0158973], [\"TRSRJDM128F9336B32\", 0.0158973], [\"TRTXDLS128F92DE76C\", 0.0158143], [\"TRDKQWQ128F92DFC27\", 0.0156384], [\"TRGBXCO128F930EE33\", 0.015617199999999999], [\"TRTPSGW128F933B5C7\", 0.0155785], [\"TRLNSKX128F933B5C8\", 0.0152941], [\"TRSOZEJ128F1495C22\", 0.0152044], [\"TRWDQWZ128F421481F\", 0.0152021], [\"TRYXHGN128F930934A\", 0.0151424], [\"TRPPXGX128F42AB8A7\", 0.0151424], [\"TRBOHVZ128F9336973\", 0.0151407], [\"TRWLPDV12903CEBFEA\", 0.015140499999999999], [\"TRGTUJC128F930EAA5\", 0.0151387]], \"tags\": [], \"track_id\": \"TRBECIP128F42515B1\", \"title\": \"Sensemilla\"}",
			"{\"artist\":\"Jack Johnson\",\"track_id\":\"TRAEUIW12903D018F0\",\"similars\":[[\"TRGRKMN128F4280B9D\",0.0644687],[\"TRHORSP128F92E4494\",0.0644687],[\"TRCSABN128F4282231\",0.0644687],[\"TRRVKWW128F4282232\",0.0644687],[\"TRBZZOG128F9314B47\",0.0638235],[\"TRMGAAS128F93141FA\",0.0553049],[\"TRNDRYG128F427261D\",0.0401141],[\"TRTYKGX128F42436D4\",0.0400664],[\"TRXLXNH128F4272614\",0.0400664],[\"TRGAMZS128E078AA8C\",0.0335228],[\"TRCVNCY128F14AA1AF\",0.0327786],[\"TRJUASI128F423E59A\",0.0321097],[\"TRRZFQA128F425F1A3\",0.0319344],[\"TRIUNNZ128F92DD51A\",0.0319344],[\"TRRTICS128F146CC20\",0.0303104],[\"TRNTDPV12903D05325\",0.0301135],[\"TRBGRXO128F4282115\",0.0284236],[\"TROHRUF128F42AD54A\",0.0284236],[\"TRREYFF128F93048E9\",0.0279258],[\"TRXLPRQ12903D05327\",0.0278501],[\"TRJHNXU12903CBA929\",0.0275302],[\"TRDFPDF128F42BC123\",0.0275302],[\"TRMZXLF128F4260DEA\",0.0245109],[\"TRTLOKR128F426AE97\",0.0245109],[\"TROOJUG128F9336CAC\",0.0241909],[\"TRJXUBJ128F92CC8F1\",0.023094],[\"TRDTRNI128F425C36D\",0.0211322],[\"TRZDDJV128F9304901\",0.0208526],[\"TRSLCJL128F428C731\",0.0204531],[\"TRUPHMJ128F42980F7\",0.0204399],[\"TRWUFEW128F14782F3\",0.0201714],[\"TRWZXKR128F92FB368\",0.0198039],[\"TRIIMEW128F92FB32F\",0.0198039],[\"TRKSCMN128F14A1C62\",0.0193932],[\"TRCHFES128F427953A\",0.0193442],[\"TRVYOCS128F92FB37C\",0.0193442],[\"TRDANFI128F42A6E56\",0.0191904],[\"TRKTFAD12903CB80A0\",0.0178375],[\"TRAQYKM12903CF7B39\",0.0175248],[\"TRXOXZU12903CB809D\",0.0175248],[\"TRCLHAC128F428BA79\",0.0174727],[\"TRUNPGT128F4256A40\",0.0174087],[\"TRWYGWI12903CBA626\",0.00333155],[\"TRKSBPB128F92F20DC\",0.00304306],[\"TRBDTHG128F932CB9C\",0.00262484],[\"TRASGVG128F92E8E0F\",0.00262484],[\"TRLFSZQ128F9336AE2\",0.00262484],[\"TRLFBXF12903CCC08D\",0.00251803],[\"TRDTWWZ12903CC36D8\",0.00200695],[\"TRTZPKI128F92FDBB7\",0.00196771],[\"TRQMVEE128F935023D\",0.00180804],[\"TREMDNV12903CAC420\",0.00180804],[\"TRBQJBF12903CB1239\",0.00176627],[\"TROIHOR128F93368CC\",0.00176627],[\"TROZOZY12903CB6376\",0.00167836],[\"TRHMSOG128F145A184\",0.00166504],[\"TRQLJDU12903CB6367\",0.00147694],[\"TRBXUWB128F149DA2D\",0.00147023],[\"TRRUESA128F9302A03\",0.00145983],[\"TRDDZMP128EF35F98E\",0.00145983],[\"TRGTIIJ128F424DA28\",0.0013855],[\"TRTOOXN128F931E7BC\",0.0013855],[\"TRUOLYY128E078910C\",0.0013855],[\"TRDJZVJ128E0789984\",0.0013855],[\"TREJROQ128F930DF3E\",0.0013697],[\"TRKJSTN128F930DD93\",0.0013697],[\"TRAMCJI128F93335AF\",0.00136497],[\"TRALDUE128F9312AF2\",0.00136497],[\"TRNIIRP128F92EB26A\",0.00134856],[\"TRJHJSK128F427D882\",0.00134856],[\"TRZYSNK128F932F813\",0.00134813],[\"TRNIQYK12903CCD224\",0.00134813],[\"TRNADKK128F92E01CB\",0.00129542],[\"TRANKTK128E07921D9\",0.0012951],[\"TRZGGPU128F932E1D8\",0.00122628],[\"TRCIVWB128F92FDBB3\",0.00118041],[\"TRZZKYE12903CC07BD\",0.00116371],[\"TRESUQO128F932E1CC\",0.00116371],[\"TRSKVVL12903CF12F0\",0.00116371],[\"TRQRBJK12903CCC08F\",0.00113921],[\"TRBDOOK128F92D0038\",0.00109939],[\"TROLREE128F9305E70\",0.00109939],[\"TRXBTXG128F9305E78\",0.00109939],[\"TRBUCED128F149F6B5\",0.00105283],[\"TROVEVL128F932A447\",0.00105283],[\"TRBZIWR128F92E58B8\",0.00100719],[\"TRZFQZF12903CD6F42\",0.00100239],[\"TRVHLMT128F9339265\",0.00100239],[\"TRZODCU128F933923E\",9.83027E-4],[\"TRUHUNN128F92E58B5\",9.71117E-4],[\"TRSXOSG128F933BB3D\",9.6139E-4],[\"TRCUFYM12903CC389B\",9.46909E-4],[\"TRKOSKR128F9305E75\",9.46909E-4],[\"TRCUEPI128F4278E10\",9.22388E-4],[\"TRIRDUA128F9306C48\",9.22388E-4],[\"TRKLQNU128F42A6E3D\",9.05833E-4],[\"TRCVBOV12903CFE8DF\",8.99755E-4],[\"TRLLKML12903CACE82\",8.99755E-4],[\"TRFHYOH128F9358CEF\",8.84081E-4],[\"TRWISJR128F931BAE8\",8.53313E-4],[\"TRFIARH12903CE1D8F\",8.53313E-4],[\"TRPXEVO128F934ADFE\",8.53313E-4],[\"TRCUKHR128F92DF656\",8.46189E-4],[\"TRJKXEZ128F92E6861\",8.46189E-4],[\"TRKPRLO128F426624C\",8.46189E-4],[\"TRBCHPT128F425E20D\",8.38022E-4],[\"TRFOFXT128F425EE1D\",8.38022E-4],[\"TRBBQPP128F92EE611\",8.35283E-4],[\"TRSTIOF128F9322A06\",8.35283E-4],[\"TRMKRWX128F1497123\",8.34705E-4],[\"TRVDAWN128F149BCE1\",8.34705E-4],[\"TRBXSRW128F931BAE6\",8.20044E-4],[\"TRRKJRT12903CB5941\",7.92591E-4],[\"TREYVKJ128F931E1B0\",7.84428E-4],[\"TRWJTJO128F42807BD\",7.61289E-4],[\"TRHTBOF128F4280810\",7.61289E-4],[\"TRFPDQA128F9306C46\",7.61289E-4],[\"TRUEGSI128F42809F1\",7.61289E-4],[\"TRDSUJR128F4278EE3\",7.61289E-4],[\"TROOIPQ12903CB0E84\",7.61289E-4],[\"TRFPXXU128F933D7EB\",7.34442E-4],[\"TRVACKX128F92EC704\",7.34442E-4],[\"TRZDDYG12903CE46B2\",7.27121E-4],[\"TRXJBSD128F146BCDE\",7.11378E-4],[\"TROQIOE12903D0C409\",7.10631E-4],[\"TROODYK12903CB5C2C\",7.10631E-4],[\"TRSJKLY128E079849E\",6.99445E-4],[\"TRAEJFS128F92DE220\",6.8069E-4],[\"TRVZQUC128F92EC15D\",6.8069E-4],[\"TRHWLMP12903CC44A8\",6.80467E-4],[\"TRAWHNS128E0785B01\",6.80467E-4],[\"TRNXWBQ12903CEB011\",6.53557E-4],[\"TRBMRAI128F427B204\",6.10571E-4],[\"TRXUWEC128F426BE3F\",6.1017E-4],[\"TRHLCZD128F426AE94\",6.00002E-4],[\"TRLHLDO128F4243642\",5.89562E-4],[\"TRQFKXE128F4273B76\",5.7651E-4],[\"TRHZVKO128E078EBD9\",5.7618E-4],[\"TRCWRWG128E078EBCB\",5.7618E-4],[\"TRRCLYV128F425FA42\",5.6908E-4],[\"TREIPPH128F930896F\",5.60064E-4],[\"TRNGQPF128F42AC92B\",5.59646E-4],[\"TROWTPL128F931E1AF\",5.54682E-4],[\"TRHLEVU128E0792E39\",5.53693E-4],[\"TREKVHW128F146BCDD\",5.49575E-4],[\"TRTKIHH128F4243649\",5.4399E-4],[\"TRIYYFR128F429537F\",5.42178E-4],[\"TREORMH128F429C47B\",5.15134E-4],[\"TRSNIYF128F42AC7D6\",5.06906E-4],[\"TRCRCBT128F4260DD1\",5.06885E-4],[\"TRBTYQQ128F1477163\",5.05121E-4],[\"TRHSVTO128F149BA75\",5.03928E-4],[\"TRQZJPG128F149BA70\",5.03105E-4],[\"TRCVABF128F42ACA6A\",5.02845E-4],[\"TRQDSLP128F4277D98\",5.02254E-4],[\"TRVWJWZ128E078596F\",5.02218E-4],[\"TRIGBQK128F4263262\",5.02084E-4],[\"TRAOOVE128F42AF13E\",5.01713E-4],[\"TRFGGTB128F427AA8E\",5.01685E-4]],\"title\":\"You And Your Heart\",\"timestamp\":\"2011-08-10 18:49:28.649234\",\"tags\":[[\"jack johnson\",\"100\"],[\"rock\",\"85\"],[\"pop\",\"71\"],[\"Surf\",\"57\"],[\"singer-songwriter\",\"57\"],[\"surf rock\",\"28\"],[\"joy\",\"21\"],[\"songs your mother are okay with you listening to\",\"21\"],[\"2010\",\"14\"],[\"10s\",\"14\"],[\"all summers\",\"14\"],[\"Like\",\"14\"],[\"songs from the bottom of my cookie jar\",\"14\"],[\"chill\",\"14\"],[\"guitar\",\"14\"],[\"Love\",\"14\"],[\"indie\",\"14\"],[\"songs I need to tag but I dont know where they belong\",\"14\"],[\"alternative\",\"14\"],[\"beautiful\",\"14\"],[\"radio friendly\",\"7\"],[\"Hitparade 2010\",\"7\"],[\"Channel X\",\"7\"],[\"radiocafe\",\"7\"],[\"You and your Heart\",\"7\"],[\"gdy rozpierdala mnie euforia\",\"7\"],[\"riding-my-bike\",\"7\"],[\"2010 surf acoustic ofh\",\"7\"],[\"best-slow\",\"7\"],[\"best-nice\",\"7\"],[\"2011 ACOUSTIC OFH\",\"7\"],[\"fuwa fuwa\",\"7\"],[\"list-datomm\",\"7\"],[\"relax and easy\",\"7\"],[\"best of 2010\",\"7\"],[\"My Timeline\",\"7\"],[\"-4\",\"7\"],[\"2k10 mix\",\"7\"],[\"fresh\",\"7\"],[\"calm\",\"7\"],[\"Happy Music\",\"7\"],[\"slow\",\"7\"],[\"California\",\"7\"],[\"Radio\",\"7\"],[\"soft\",\"7\"],[\"piano\",\"7\"],[\"Beach\",\"7\"],[\"pub\",\"7\"],[\"harmonic\",\"7\"],[\"americana\",\"7\"],[\"modernist\",\"7\"],[\"loved\",\"7\"],[\"CD\",\"7\"],[\"eddie\",\"7\"],[\"Light Music\",\"7\"],[\"my fave tracks\",\"7\"],[\"acoustic\",\"0\"],[\"aarh\",\"0\"],[\"Ian A fraser\",\"0\"],[\"me\",\"0\"],[\"wishlist\",\"0\"],[\"heart\",\"0\"],[\"Cool video\",\"0\"]]}",
			"{\"artist\":\"Jack Johnson\",\"track_id\":\"TRAOWBP128F4257C64\",\"similars\":[[\"TREWIWV128F4252E19\",0.983528],[\"TRAOHNH128F427262A\",0.105584],[\"TRYLOUW128E07835F7\",0.105584],[\"TRBWOJX128F92FD034\",0.103713],[\"TRFEMMX128F425D520\",0.103713],[\"TRFHZRA128F42BCE86\",0.0979031],[\"TRPKFHG128F92FD101\",0.0979031],[\"TRAAFOY128F146CC17\",0.0978161],[\"TRTSLGM128F92CF812\",0.0946486],[\"TRBGCQP128F92E36CB\",0.0775811],[\"TRNTDPV12903D05325\",0.073224],[\"TRGAMZS128E078AA8C\",0.0703495],[\"TRDTRNI128F425C36D\",0.0697527],[\"TRBGRXO128F4282115\",0.0678945],[\"TROHRUF128F42AD54A\",0.0678945],[\"TRCVNCY128F14AA1AF\",0.0655418],[\"TRQMZYT128F425EF0F\",0.0612291],[\"TRIRNFG128F42BC12B\",0.0612291],[\"TROOJUG128F9336CAC\",0.061173],[\"TRIYYFR128F429537F\",0.0606308],[\"TRJHNXU12903CBA929\",0.059374],[\"TRDFPDF128F42BC123\",0.059374],[\"TRDANFI128F42A6E56\",0.0579983],[\"TRXNXPH128F14856C9\",0.0579449],[\"TRWXGMQ128F92E8B59\",0.0579203],[\"TRAJTRI128F146525B\",0.0577298],[\"TRYJQFX128F14856CF\",0.0576054],[\"TRNPXII128F1488557\",0.0559502],[\"TRHURLC128F931E1B5\",0.0557691],[\"TRPPOJC128F42269FF\",0.0556421],[\"TRDCXRU128F92C77E5\",0.0556421],[\"TRXUBFV128F42410C4\",0.0556421],[\"TRFZCOL128F425C770\",0.0549482],[\"TRDJIPJ128F9321317\",0.0537577],[\"TRMBLKZ128F429538B\",0.0529299],[\"TRKLQNU128F42A6E3D\",0.0527426],[\"TRVMKOU128F148DAC0\",0.0524895],[\"TROWTPL128F931E1AF\",0.0521687],[\"TRZFQZF12903CD6F42\",0.0521171],[\"TRVHLMT128F9339265\",0.0521171],[\"TRRYCUN128F42821E1\",0.0516367],[\"TRNUOIN128F4274612\",0.0516367],[\"TRYKVDT128F425C371\",0.0498359],[\"TRKRJDA128F1467E2E\",0.0495322],[\"TREQGNF128F14B0286\",0.0493747],[\"TROFZQO128F4299E21\",0.049022],[\"TRNQYTS128F92FB350\",0.0472225],[\"TRTAYDQ128F92FB389\",0.0472225],[\"TRLNBCO128EF351C56\",0.0472225],[\"TRUNPGT128F4256A40\",0.046592],[\"TRBZIWR128F92E58B8\",0.0444628],[\"TRHJGCA128F92FD364\",0.042762],[\"TRRKPKZ128F42AA790\",0.042762],[\"TRTRUKB128F92CA9CD\",0.042762],[\"TROXFTV128F92ED878\",0.042762],[\"TRUQZXA128F42A5F85\",0.0378812],[\"TRMZXLF128F4260DEA\",0.0375607],[\"TRTLOKR128F426AE97\",0.0375607],[\"TREHBJW128F4288748\",0.03587],[\"TRFZPOL128F92C47AB\",0.0352576],[\"TRYBFNR128F426BE3D\",0.0352215],[\"TRSLXOX128F4280AB4\",0.0348133],[\"TROVHGG128F93181C9\",0.0346125],[\"TRBFURI128F1455498\",0.0339155],[\"TRAMQXC128F14A2378\",0.0338592],[\"TRIUHEV128F92F53FA\",0.0306698],[\"TRHLZCO128F42AA794\",0.0305742],[\"TRRLPJX128F92ED871\",0.0305742],[\"TRFZUYL128F92CA9C6\",0.0305742],[\"TRTWWAT128F92FD3E1\",0.0305742],[\"TROMSXK128F9318EAB\",0.0300888],[\"TRYMWYE128F92DBB65\",0.0300888],[\"TRRWNOC128F426609D\",0.029373],[\"TRZEOWE128F4294FE6\",0.0286188],[\"TRECMFY128F42666D9\",0.0276543],[\"TRDPFZI128F4262CC8\",0.027654],[\"TRXTRAU128F4231294\",0.027654],[\"TRWHCZK128F931822E\",0.0269238],[\"TRXSMUD128F4296A8D\",0.0262302],[\"TRKJDSS128F4262A09\",0.0262302],[\"TRKRADU128F92E17F6\",0.0258697],[\"TRNGIYP128F92E6865\",0.0255374],[\"TRUURMN128F4266275\",0.0255374],[\"TRUGJBD128F42710AA\",0.0231481],[\"TRMIVBJ128F93379E7\",0.0218528],[\"TRXWFNS128F9336C8B\",0.0210962],[\"TREUFZM128E0780DAA\",0.0183511],[\"TRFEVHL128F42670CB\",0.0179301],[\"TRMXKVJ128F1484E1B\",0.0013926],[\"TRAZBYL12903CD8951\",0.0013926],[\"TRXEDHO128F92CC900\",0.00135407],[\"TRRUESA128F9302A03\",0.0013286],[\"TRDDZMP128EF35F98E\",0.0013286],[\"TRFSQAD128F145F84E\",0.00132798],[\"TRRAYQT128F42B80F1\",0.0013277],[\"TRGTFPN128F4243059\",0.00131896],[\"TRZBGXQ128F42A0A34\",0.00130748],[\"TRKTFAD12903CB80A0\",0.00129165],[\"TRCTTWP128F146CC14\",0.00127213],[\"TRBXUWB128F149DA2D\",0.00125501],[\"TRQEHBQ128EF36772B\",0.00124854],[\"TRUMXQQ128F932C290\",0.00124015],[\"TRTVDEI128F92FA548\",0.00122149],[\"TRNOJLD128F42AD3A0\",0.00119854],[\"TRLZNHU128E07859BF\",0.00118544],[\"TRIRXXB128F422D34F\",0.00117565],[\"TROWYGT128F9323490\",0.00117557],[\"TRCTBDR128F42AD6B3\",0.00115625],[\"TRFWSXG128F426AE9A\",0.00114263],[\"TRTUMZM128E0785146\",0.00112218],[\"TRFDCLW128F429713C\",0.0011215],[\"TRZUCIS128F92F91B9\",0.00109566],[\"TRRUCFC128F92CC902\",0.00109041],[\"TRGFWML128F92DFAE4\",0.00108288],[\"TRVBHHT12903CB9ECE\",0.00107432],[\"TRZMWGT128F92EFD40\",0.00106757],[\"TRNYQKJ128F92EDF45\",0.00106757],[\"TRVYIPV128F92F3C2B\",0.00106757],[\"TRNQXWK128F428B659\",0.00106401],[\"TRQREJG128F4296092\",0.00105987]],\"title\":\"Monsoon\",\"timestamp\":\"2011-08-01 18:50:02.209431\",\"tags\":[[\"acoustic\",\"100\"],[\"singer-songwriter\",\"78\"],[\"rock\",\"43\"],[\"pop\",\"39\"],[\"chill\",\"34\"],[\"Mellow\",\"26\"],[\"male vocalists\",\"17\"],[\"piano\",\"13\"],[\"rainy day music\",\"8\"],[\"jack johnson\",\"8\"],[\"relax\",\"8\"],[\"vocal\",\"8\"],[\"Acoustic Rock\",\"8\"],[\"songs about living\",\"4\"],[\"2010 ACOUSTIC OFH\",\"4\"],[\"freakey stylish\",\"4\"],[\"the cure for the suffering soul\",\"4\"],[\"bobbypop\",\"4\"],[\"new2\",\"4\"],[\"steve radio\",\"4\"],[\"m singer-songwriter\",\"4\"],[\"annymix\",\"4\"],[\"piano-centric\",\"4\"],[\"Heejj\",\"4\"],[\"72 bpm\",\"4\"],[\"2008\",\"4\"],[\"tcalternative\",\"4\"],[\"strength\",\"4\"],[\"slow\",\"4\"],[\"english\",\"4\"],[\"feelgood\",\"4\"],[\"soft\",\"4\"],[\"Laid-back\",\"4\"],[\"roots\",\"4\"],[\"00s\",\"4\"],[\"Mellow Music\",\"4\"],[\"super\",\"4\"],[\"undecided\",\"4\"],[\"RockPop\",\"4\"],[\"Monsoon\",\"4\"],[\"chillout\",\"0\"],[\"triest\",\"0\"],[\"songs i like\",\"0\"],[\"sick\",\"0\"],[\"surf music\",\"0\"],[\"collection\",\"0\"]]}"
	};
	
	/* Helper function */
	private ArrayList<String> constructSimilars() {
		ArrayList<String> similars = new ArrayList<String>();
		similars.add("TROBUDC128F92F7F0B");
		similars.add("TRWSCCK128F92F7EDB");
		return similars;
	}
	
	/* Helper function */
	private ArrayList<String> constructTags() {
		ArrayList<String> tags = new ArrayList<String>();
		tags.add("1980s");
		tags.add("Alternative");
		return tags;
	}

	
	@Test
	/* Tests the song constructor that takes all info as parameters */
	public void testSongConstructorParameters() {
		Song song = new Song(ARTIST, TRACKID, TITLE, constructSimilars(), constructTags());
		assertTrue(song.getArtist().equals(ARTIST) &&
				song.getTitle().equals(TITLE) &&
				song.getTrackId().equals(TRACKID) &&
				song.getSimilars().equals(constructSimilars()) &&
				song.getTags().equals(constructTags()));
	}
	
	@Test
	/* Tests the song constructor that takes info from JSONObject */
	public void testSongContructorJson() {
		JSONParser parser = new JSONParser();
		JSONObject obj;
		try {
			obj = (JSONObject) parser.parse(songs[0]);
		} catch (ParseException e) {
			fail("testSongContructorJson: invalid JSON");
			return;
		}
		Song song = new Song(obj);
		assertTrue(song.getArtist().equals(ARTIST) &&
				song.getTitle().equals(TITLE) &&
				song.getTrackId().equals(TRACKID) &&
				song.getSimilars().contains(constructSimilars().get(0)) &&
				song.getTags().contains(constructTags().get(0)));
	}
	
	@Test
	/* Tests MusicLibrary.getTrackById() method when song with queried trackId exists in library */
	public void testLibraryGetByTrackId() {
		MusicLibrary library = new MusicLibrary();
		JSONParser parser = new JSONParser();
		Song song = null;
		try {
			for (int i  = songs.length - 1; i >= 0; i--) {
				song = new Song((JSONObject) parser.parse(songs[i]));
				library.addSong(song);
			}
		} catch (ParseException e) {
				fail("testLibraryGetByTrackId: invalid JSON");
				return;
		}
		assertSame(song, library.getSongById(TRACKID));
	}
	
	@Test
	/* Tests MusicLibrary.getTrackById() method when song with queried trackId does NOT exist in library */
	public void testLibraryGetByTrackIdNull() {
		MusicLibrary library = new MusicLibrary();
		assertTrue(library.getSongById(TRACKID) == null);
	}
	
	@Test
	/* Tests MusicLibrary.getSongsByArtist() method when songs by queried artist exist in library */
	public void testLibraryGetByArtist() {
		MusicLibrary library = new MusicLibrary();
		JSONParser parser = new JSONParser();
		JSONObject[] jsonSongs = new JSONObject[5];
		try {
			for (int i = 0; i < songs.length; i++) {
				jsonSongs[i] = (JSONObject) parser.parse(songs[i]);
				library.addSong(new Song(jsonSongs[i]));
			}
		} catch (ParseException e) {
			fail("testLibraryGetByArtist: invalid JSON");
			return;
		}
		assertTrue(library.getSongsByArtist("Jack Johnson").size() == 2 &&
				library.getSongsByArtist("Jack Johnson").first().getTrackId().equals("TRAOWBP128F4257C64"));
	}
	
	@Test
	/* Tests MusicLibrary.getSongsByArtist() method when songs by queried artist do NOT exist in library */
	public void testLibraryGetByArtistNull() {
		MusicLibrary library = new MusicLibrary();
		assertTrue(library.getSongsByArtist(ARTIST) == null);
	}
	
	@Test
	/* Tests MusicLibrary.getSongsByTitle() method when songs with queried title exist in library */
	public void testLibraryGetByTitle() {
		MusicLibrary library = new MusicLibrary();
		JSONParser parser = new JSONParser();
		JSONObject[] jsonSongs = new JSONObject[5];
		try {
			for (int i = 0; i < songs.length; i++) {
				jsonSongs[i] = (JSONObject) parser.parse(songs[i]);
				library.addSong(new Song(jsonSongs[i]));
			}
		} catch (ParseException e) {
			fail("testLibraryGetByTitle: invalid JSON");
			return;
		}
		assertTrue(library.getSongsByTitle("Exception To The Rule").size() == 1 &&
				library.getSongsByTitle("Exception To The Rule").first().getTrackId().equals("TRBECDN128F4225717"));
	}
	
	@Test
	/* Tests MusicLibrary.getSongsByTitle() method when songs with queried title do NOT exist in library */
	public void testLibraryGetByTitleNull() {
		MusicLibrary library = new MusicLibrary();
		assertTrue(library.getSongsByArtist(TITLE) == null);
	}
	
	@Test
	/* Tests MusicLibrary.getSongsByTag() method when songs with queried tag exist in library */
	public void testLibraryGetByTag() {
		MusicLibrary library = new MusicLibrary();
		JSONParser parser = new JSONParser();
		JSONObject[] jsonSongs = new JSONObject[5];
		try {
			for (int i = 0; i < songs.length; i++) {
				jsonSongs[i] = (JSONObject) parser.parse(songs[i]);
				library.addSong(new Song(jsonSongs[i]));
			}
		} catch (ParseException e) {
			fail("testLibraryGetByTag: invalid JSON");
			return;
		}
		assertTrue(library.getSongsByTag(TAG).size() == 3 &&
				library.getSongsByTag(TAG).contains(library.getSongById("TRBDCAB128F92F7EE4")) &&
				library.getSongsByTag(TAG).contains(library.getSongById("TRAEUIW12903D018F0")) &&
				library.getSongsByTag(TAG).contains(library.getSongById("TRAOWBP128F4257C64")));
	}
	
	@Test
	/* Tests MusicLibrary.getSongsByTag() method when songs with queried tag do NOT exist in library */
	public void testLibraryGetByTagNull() {
		MusicLibrary library = new MusicLibrary();
		assertTrue(library.getSongsByTag(TAG) == null);
	}
	
	@Test
	/* Tests MusicLibrary bulk song add method */
	public void testLibraryAddSongsByHashset() {
		MusicLibrary library = new MusicLibrary();
		HashSet<String> hs = new HashSet<String>();
		for (String s: songs) {
			hs.add(s);
		}
		library.addSong(hs);
		assertTrue(library.getSongById("TRBDCAB128F92F7EE4") != null &&
				library.getSongById("TRBECDN128F4225717") != null &&
				library.getSongById("TRBECIP128F42515B1") != null &&
				library.getSongById("TRAEUIW12903D018F0") != null &&
				library.getSongById("TRAOWBP128F4257C64") != null);
	}
}
