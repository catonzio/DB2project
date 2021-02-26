-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: db2project
-- ------------------------------------------------------
-- Server version	8.0.22

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `EMAIL` varchar(255) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `USERNAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `EMAIL` (`EMAIL`),
  UNIQUE KEY `USERNAME` (`USERNAME`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (1,'admin@gmail.com','4dm1n','admin');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dirtyword`
--

DROP TABLE IF EXISTS `dirtyword`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dirtyword` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `LANGUAGE` varchar(255) DEFAULT NULL,
  `WORD` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dirtyword`
--

LOCK TABLES `dirtyword` WRITE;
/*!40000 ALTER TABLE `dirtyword` DISABLE KEYS */;
/*!40000 ALTER TABLE `dirtyword` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) DEFAULT NULL,
  `PHOTOIMAGE` longblob,
  `PRODUCTOFTHEDAY` date DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `PRODUCTOFTHEDAY` (`PRODUCTOFTHEDAY`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'product 1',_binary 'ÿ\Øÿ\à\0JFIF\0\0\0\0\0\0ÿ\Û\0„\0		\n\n	\r\r\r \"\" $(4,$&1\'-=-157:::#+?D?8C49:7\n\n\n\r\r\Z\Z7%%77777777777777777777777777777777777777777777777777ÿÀ\0\0\Ä\0\Ä\"\0ÿ\Ä\0\0\0\0\0\0\0\0\0\0\0\0\0\0ÿ\Ä\0E\0	\0\0\0\0\0!1A\"Qaq2‘¡±Rb‚Á\Ñ#Br¢\Âğ$c\á4CS„’“”\Òÿ\Ä\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0ÿ\Ä\0\0\0\0\0\0\0\0\0\0\0\0\0\0!ÿ\Ú\0\0\0?\0\î(ˆ€ˆˆˆ€ˆˆˆ€ˆˆˆ€ˆˆˆ€ˆˆˆ€ˆˆˆ€ˆˆˆ€ˆˆˆ€ˆˆˆ€ˆˆˆ€ˆˆˆ€ˆˆˆ€ˆˆˆ€ˆˆˆ€ˆˆˆ€ˆˆˆ€ˆˆˆ€ˆˆŠ\Ü\ÓGn–ims\Ş\à\0ó%\ÄTC,s\Ä\Ùa‘²Fñ–½‡!Ã¼Z\" \"\"\" \"\"\" \"\"\" \".a©ºa¡·:x-VúŠ™\á•\Ñ:I‡W-\Ü~\Ñ\Ş1‚\r¯Z\ë+n¢j\í©gœ–\Ó\ÓF{R\Ç\È\r\Ù?5Æ¯=*\ê+“¥}=H·S\à\â*|‘\âò2Oˆ\Â\ÔoWZ‹\Õ\ÂjË\\µR\É\Ú\Î\Ü\Üş\ËG ;‚›\è¿MMª`†HóCGŠŠ²x8\Ùg\Ş?\0\åV>Š\ÓO­“N\Ú\äºg\éÏ¤‰\Õ=ah\ÚÏr¤‘A=D\âmQMD\ì\ÃHúŸ´\\ \Ú÷©g½‘´¾G´q.8sË®¡‚\Û\Ò%}{¤\ÑR\é“;Œd\â&8\0÷7M€\Üc…¡´\âµ\æ&!¥øû\åş¹Rª3M\Ó\ÏMc£ef\r[£\ëjx^vß\r§&€ˆˆˆ€ˆˆˆ€ˆˆˆ€­\ÔL\Êx$šSˆ\ãi{8\02V-\î\íId¶Oq¯“b[“.<€\ï$\îk•\îz†šª\Ó<vúY™(aÛ’Xøch\î¿z]\Ó\rşz›;Eºˆ´†¸5²HA;‰$`Ü»\Ï+›\ÇV\Zd/ò²bK\Ú\íû\Ïdóæ«šc+†@v\ì5­\n\Ät$œ¼á§ƒ[¼­Ez0|W\ĞıPCO¢\İX\Ğ:\êÚ¹#¿t\ìğÏ©\\\ÂÖ³\02#Œ`œ¸şKt\è\ã¤9ôlrQT\Ó\Z\Ët²u¤5ø|N8·;·\ã\Ù8\ß\ÍMH¢‚\ÓZ¾Ç©£Í¦¹’J_û³Í§¨Ü§TAbÜ®v\Ê)k.1S\ÓD2ù$v\097O7‡6¢\İhcû\"3Q,y\ÜI;,\Èğ\Ã\×+³\ßA\r\Ú\'\Ä\\Úºx\Ø\ÇrŒ²fH6‡6œ@\ïR\"j6\ê=GSpc]$\àk\Æ#o3\ÜNóYZ”ƒpiİµ\Ú#¸óü•W\Øö\Ú\Úk•”S\Ç=<\Ìd±œµÁd®cıŸe¨~Š“g©ºFÀO\Õ\ÃI\Ç\Ş.]9Djº\Ï[[´\İ$­lñOq\Ùı]+]’{ñ\ì·\ç\Éjúû¥X­oİ§L\Õ0¾©\îÚ7w4h|\ï\\Bw\ÔW¹ó½Î’G’ó#İ€I\ŞI\ïTJİµ…\îø\Ép¹\Ô\É“a¤\Ä4nP7\Ìò\ç\rœ\Û<}\Ö\ÂZ\í²\à\ãŸhğ÷sGHA\í‚>_‚ªõ\êš\àÑ³õ»\Ï\ä©5.Á\È\0sÀ\â¬:NŸ5a\ï$¨/¾p8+n”¸w*p<\Õ9$€\ĞI\'€\ïUWbhdd°\È\è\äa\Ë\Çl¹§¼À®—¤:g»Ú‹)µf\çH7u\Ãvúğ®Š\Ò\ã\ÒõqR\n\ÛÌ­¶R;\Ø3´™$=Í‰>xQu\Ó	‰¥l‚ 0Ş¸‚\çxœn\îø”G~¿t\Óc‚…®°µõ\Õ278•Ÿ·‘’|Ÿ5\Èu©»jJ®¾º¥ó–œ·k³?º\Ş\ró\â{\ÔU¾\Ó5l¥‘SK4ÀmuQ;\ÜG²<>Ix¢}\æ=<cœ\×2vX\æ’ ‚ñ\ÏyHE±i‡nO®x ~gÜ¬\ç\r‘\Ç%\ÎşŠ·µ»róm\Ü?³Ìš[Å¢C¾9S\Ï\Ö.øµ¾õÙ—\É\İ\Ş&³kkUL/!’\Î\Úy€>\Ôr\Ò©\Ì/¬VuC_uUÀ1t¸\Ã¤dBÔ‡Éƒ\'\×&Qr[\ßLÑ°=–+a~\éªİ\á\Ønó\ïJºt‹ªköÃ®§fGf˜°<\Çk\âƒèªŠ˜)c2T\Í,]#\ÃG¼­f\ç\Ò.–·\ä:\æÊ‡\ç­2d÷dnø¯ªªæª•\Ò\Õ\Îù\å|\Ò;$\ïT\Ç3cË‰N7 \ìw˜cm¶\Ñ!q\àje\rÇ‰ksóZ>£\×\Zò\Â\É+\Ì08ö §ıSH\î\Ï\äJ\Õ[V\09v÷qV\æ©\Úqß•Ub:V—öpì‘—rõ\á\'#k³\ç;ü©&#öA\Îñ\à‡L\ŞDŒ\È\Ê³¿;EÀ÷Ÿ±\ß 9\í\ã¯U\ã¤<A\ïVÀ$z$$ñ;\Îò­•\"o\'\Í\ÅUu˜ú<£¾\é}>ø\ê\İK$tsœ\Æ‰:\Ì=\İ\Ü\É\ß\â£\îUšg@µ\ÔÖŠv\Üo`a\ÓLvº£öˆ\Ü\Ùnşõ¤Ã¨¯\Ô\Ö\Ñm§ºT\ÇF\r\Æ\ä<2¢q¡²\Ñ\à‰—u\â¹õuó¾i\İ\Å\Î8\rƒG’½j¦eMt0–1Ä™&\Æö0\\Z9vAÿ\0Eµ‘€0\Ş8\ïñ*¦\Ë${[svšZpxƒ¸$X\Ü*®ö›M<t\Ö}©\äad­{p¶	p.8Ë½­\í\îkFs•\"š¾?¦]ë¤!±\×JLv?\á\Æ	\í;x;\È\çf\Î\î<=UªŠ‰j^3Ë‹[²\ÑÀ5£€ğA~ºªIô*~¦\0{=c¶\ä>.w\ä\0X\âa\Ğõ\n\ĞkÁ¤ù\æq\è%ôü­‡PZ\æn±Y±\äğW\Ù+\âX\Ñ\Ê\×@\ç¶`FÁŒ\àyc\×\Ùö ´\Ñ	\ät’Šxö\Ş\ã½\Î\Ù%MMe¢\"ˆ\å\İ5\ëJ\ë\r-qEax’fûlc@\Î\É\äIv3Ç;\×\n¯¬ºW9òe\Îy\ÉvGyø®­\Ó‘»]¯V[\â5\rŠ71\Ñ\r¡——\ã\íc\Ñrª\ë}½Å•´\Ó@\ïóX[óZ\\^‰ñ83mùkƒš\îürõ?‚Ì¶S›…]=$ IQP6ZÀ}©3†·\å¿\ÇÁAõRqùÜ«¦’¦’x§¦‘ñM¶£{\×w‚¢·I4½MbKĞŠ\ß¢9uWe\Ïx?°Á—x\íqQ\Ò\ÃF\"{ yycw=\í\r.9\î\É\Ç>k^¬¬«ª\Õ5“\É=C½©dys\æJ°gy\'r©µ51l\ã\\³\Éb·\0m¸\à7\ë,.°«Å­ß±‚\ß/\ë\nSÁÏ´Cˆ\íi\Ù<†ñ¹\\\É9o‡9¯Cy÷*«,f×€ù«\ìf7\ÅV\Øó\Éf\Ğ\ĞTÖ¿«¢¦š¡ù\Æ!Œ¿\Ça¶<ÁV!\İÜ·+wG:’°ú8\éZy\ÔH÷­Š‹¡ş°\ï[Ë¶y\ÇKoñ;?%R¹$’\0p\Ïz®Š\ß[r2‚’z—ŸùL.×‚\ï¶ÎtÅ¹\Íx¢uL€ûU2\ïò\à®jKj\Ò4¢8¡õna¤‹\r\'\Å\ßU¾>\ì¡\\:\ï¥/6[s+®\ÔÌ¤\îÙ’Jİ·ŸŒú¨6²i†+e½VW\ê®½U¥\à\Æ4l²&ıVCú+\Ò\Ò\Ó\r¦·Ào*®W\Ö´{\Ödv\È;@»Äª¼$šQ\r;\å•\Ç\rhiq>@)6\é\rGTÎ²\à\È\íĞ‘ªù›?s\Ú>XT¨¹j©`ì·´{™Áb\ÍX$m;>öÿ\0‚\ßl\Ú\ÛÕ‰%m\Æ\ìğ3ş\ï;´}\0Zö§·šj\×B\ê(\è\Ãw˜s\âJL+Y.qv\ÓË†ğ[»\Ñ}{£õ¿QØ©km\Ó1\à\Æ\Ñ$`¨ö¸r+\ä‚\"k±½\Äz)½+¨\î\Zn\â\Ú\ëD¢9\ç±\Ã,•¿UÃ»â³º­\ÑEi‹\Ü\ZŠ\ÅGu¥ilu\Éa9,p8sO‘\"ƒ&\á@\ÊÀ\×gfV{.\ÇÀø(§\Ğ\Ô3,’-¦÷ci§úñ[\n¤½£‹€õW6\r6ª\Ãf«\'\é–Z		\æ\êf\çß…S\Ñî“ª96\×BÈ\í\ì\áoõ–V\âI#;@4T­\ÉmdF@£œVt=`™\Å\ĞW\Üa\'–\Ó½ª>^… \'õ7\é\0î’”ğp]MÎ¥ldı:—hpa½{\íÚŒ‡´ói\ÈTr_Ğœ›[¯±‘\ãJúWB„\äºş\Ñ\â)Işe\ÖvŠ÷i\n\åğô/J\Ğ·\Ù0>¥(7):.ˆô\å?û\Å]Â¨ı§µ€\Ú\Ñó[\î\Ò…k\ÔZKPã©³C!!\Õ\Êˆ•=q\ÂÁG± \0ªEQ\á^eT´ş”\ë\æ \Ñõ?F\Ç%DŒƒi§\Ùq\íc\ÇdZ“Z\ÖJ\é¨4]\×:–™j\ãjO0;\×Ç‚\åõV=E-D³\ÜM\r,\Ò¹&¯¹B\ã\â6óğQ,·\Ä\ãP7rÀU2\ßN\Î¿\ÕÀ|–Uu\Öx\â\ÚıUnf9S¶i‡ğ³—GC£\é2\êË­Ê±Ü›OB\Ö\ê÷~ANÑº³ö½Ù§ú˜=\\\n£`ƒTiª\r‘Ab¯¨ c5WF\Ó\æ\ÈÀ\n¸úCuœ\ë.³[\Ş\î/˜9\ç\ï\ê\09ö[ºJ>¡Œ’HX{HQxÊ»k}Mxµ\Z‚\Ãûö\î\n‰^s!$ø©!<onYQ´\Şö5¸ø«MO3\"‹­šW»e‘°\å\Î=À\r\äø ŒN\'‰÷+ğ\Ò pSR\Ú\ëé¥)¬·\Ë \Ìl’–L¸xd­§L\èEv™‚J	­ô§\Ò\ÎÎ«÷OlŸw+1¡xª\éôDqOX\Ö\Ô\Êc;[\Ü\Òs“\ê\\=m\Ö[U=š\×Oo¤Š\ã\'‹O‰9+\ÔÊ“Å ù…R Æ’‚’On7z,)ôİ|õ´1Ÿ\"Gâ¥‘³>‚Ó³ûTO»3ÿ\05ŠÎl\ĞHEUt¤p9ÿ\0X\æü8¸\"\rV]P\è\Ìqj«\ä`œûP’=z¼üUtZj\çF\ç©**\ÚFj\é¢vÏ‘`i÷­B‹e\Äq¯¦wı)Îµû\ÔÕ—8kc\Õÿ\0F\êOb\Z,DG\Úiy\Ú\î\ßé…½\"´k\Û5\"\rº\Û,œ\Ş\ÚW°M³óX\ÕV}S$€\Ò\Şmp3\Ú\ë{Ÿ¿Ï¬lD£W’Ñ©]k.¶\ÖIo\èN8ô\ÛZ¦¥\è\ãS\êH\âŠ\åª\é.\Ûdl \ÙX\ÆN¿Ÿ½u4J8y\è:\èF?\Ú\nOıG\æVÿ\0Aw`\ì·P\Ò\çÆßšîˆ \á û\ß-CIÿ\0ßš¥\İ^H ^\èy˜•İ‘^óÚ¾\Ò\à×…)f\è£Q\Ùg|\Öû\å¾9İ’\ç\Ówqİ´cD¦^Œ\ïUuo«¸VXjª$\0>Im%\Ø»”Í›G\Ş\ìí…´Uº–\ì±Ì¶öÀ\á\íg$ø\åo¨‚\rD\Üuõö\çùR¼:’…µ`~¾H~\Äd~+!y½¨€ˆˆˆ€ˆˆˆ€ˆˆˆ€ˆˆˆ€ˆˆˆ€ˆˆˆ€ˆˆˆ€ˆˆˆ€ˆˆˆ€ˆˆˆ€ˆˆˆ€ˆˆˆ€ˆˆˆ€ˆˆˆ€ˆˆˆ€ˆˆˆ€ˆˆˆ€ˆˆˆ€ˆˆˆ€ˆˆ?ÿ\Ù','2021-02-25'),(2,'product 2',_binary 'ÿ\Øÿ\à\0JFIF\0\0\0\0\0\0ÿ\Û\0„\0	( %!1\"%)+../383-7(-.+\n\n\n\r\r+7-7+--++7++2-77+-+++7++.7-07+++7+77777/70,7+7,77+-ÿÀ\0\0‘]\"\0ÿ\Ä\0\0\0\0\0\0\0\0\0\0\0\0\0\0ÿ\Ä\0H\0\0\0\0\0!1AQa\"q‘2r¡±\Ñ#BRb’Áğ‚¢²34CSUc“\Â\Ò\áE³\Ã\Óñ$ÿ\Ä\0\Z\0\0\0\0\0\0\0\0\0\0\0\0ÿ\Ä\0%\0\0\0\0\0\0\0\0\0!1AaQ\"qÁğÿ\Ú\0\0\0?\0÷D@DDD@DDD@DDD@DDV+k#†Iddl\\÷´w“’àµ“¥šZpD-2»‹¯2\Û`A{¼cö· ôDZµÆ†6ƒ%\\!\ØA-p¸½°¶\î÷-UgI´ŒA\Ò<rgWÿ\0x±f‹\Ì*úd§\ÍB_ûDŸ(šñ\ïZº™æµ¢¡.v\ìE\Ìº\âü{\Z/wJZDú´x\ãÿ\0xY\r\é.¾\×t7öd?	\Ğõ\Ä^FŞ”j·\ÅN{› ÿ\0YYô«(õ©£=\Ïs~ ¦‡ª\"óx:Xgõ”®Ì\ßµl©zN¢¬&\Ú`pı\Ââ¦‡l‹IE­\Ô3[TW;VOƒ\ìV\é\\A\Ş3*ˆˆˆ€ˆˆˆ€ˆˆˆ€ˆˆˆ€ˆˆˆ€ˆˆ‹\Íõ\ë¤g\Ó\Ôz\r]mN\Çƒ[p\Æ6\ã€8‹‰\Â\İ÷\ÏvzcY)(\È\ÆH¸i7qCÍ¼¤úf¥c\Ëa†IZ\r±¸ˆƒ¸–‡\ï06._F\êF9\\ú\Ù$ªªy\ëau\Î)¥\ì†\Ş\ÆÙ°ea{.Š»G²†	fŠ\n\ÜÆ—™ûnt€6\Ç\Å\Ş((zkƒ–B-™\ëq\Ü\Ñp|\Â\êtGHš> 7\é\ÄN6%¼e®#aq\ìğH^y¬:zX%|2\Ó\Ñ\Ì\Æ\Æ\Çö›€¿û-k\ÜA ƒñ\\–•ª§˜^:Q\íı[\ì\Ñû\ÂGpš ´¾µ\Ñ\Ò_®¨Œ8}Pq¿ğ2\îñ²\ã4ŸK\Ñ©©\Üü\íŠG\Û\Şq;ğ¯+d?^\n\í‰ÛšúÛ§jô„ık‹Zmak€\Æ\ä0\Ç{¹».Mó\'€h™¡o›\ä&ü~f÷[\Å \ÅE¦\Äb¦c}V4w5|1H1.‡<a\\À¢HAr­ñ\ì8›öN\Ñ\ì¸ü˜Y\ÃK«.\äµúBº6ƒr/ºÙ›¦\Æ|ºI®~„\ì\à~G\âªe\\\Ìòb\ÏzÏ¢«.;~)±\Ñhªq;ğ\à$vn.	\á´f¯\ém%=‰\í4\ï°< º}­Å£«¨oXİ—\Ë¹\ß\'x¯g\r\á\ËŒ\æ²ü¹¾²ú\Î<\ç\'\ï\Ã\ç\Òÿ\02ÿ\0Mˆm‘0HZk¸´\ß\Ç\"±¡¨†7^šªjw_\ï\0{\ÌdeŞ²½‚§6I€Ø°ûŸù,\ÊM\\¦\î\'_\\´´s^ÿ\0ne­É§õü|g\\œ\Ó+\ã<}M\Ë4\Æ\Ñ}&WC`÷2fğ‘ :\Ü™o2\n\ìô?Jt²\ØN\ÇÀ\î?\ÎGøš1~\êò\Íge;9¹\Ï\Ú8\0|öd´Í‘ry¸\çw\íõŸŸ\Ş\âÇ’cd¿w}EC_\í\ÇŒ‘¼X\à\áİ–\Ã\Éd/˜t~‘’\ãŠGF\ï´\Ç\ãm£‘\Éz®ô¥3lÊ¨ú\áö\Ød\ï-õ]\á…j\Ós\×k4°SW3<\Íx@6s}¦œ\ÂÙ¨¢\" \"\"\" \"\"\" \"\"\" \"-&²k].m\ç”v\Æ\ŞÔ¯\î`\Ø9›h5\İ%\ëQÑ´˜\ãWˆ¢\Å\Ú9—a\ßfƒa¼–\ë\Ìá¨Fe<\Ç\Ój/%DÀu²\Æ\Ònc„Z\ÆBr³nMkV6¸\ëÌšFXœ\"lqÀó$m=·\á-k\Şí€¶÷\ró’\æ&s¤q{\Í\Ü\ì\É\ŞPvµúôÆ°CK£ˆfF,O‘\Çk¥”\ß\Îó\Úñ\\­~²I=\Ùp½\Ã{D¼óu‡%ŠØ•\ÆA\ÉQd1\\ljûX6o\á´ù+¡¼­\Şm\î>\ä\í‰]lJç\Şo \Ç?q¸òA³†|†g\È)a¿\\@¹\n§=¿®\å	\Z.\â\0\âM‚usõc\0ö¹´8…\\d\ïòZ\Ú\Í=rh\Ævpo™ù,z(*¤hkÕ³qvVûVá—ŠƒiQ3X.÷\ŞV¹\Ú_\Ãny\î6ò–u6­0R¹\Ò;ğ·\Ëo½n›GÕ´a`kN\Ì6·»z³wdğ\Æ\å%’\Ş÷ÃšE\ÔKü\ë\Ã\Z~¨\Ìû²õŸK¡¡<8öŸ\Ú>`ğw\ábp»s¶´ò<;ÕªŠr\Ãg\îqxY\Ş;1\êó\Î\\nw\Û/¿Ÿ¹ùÿ\0m\Åi(z¹œ7ˆw\Ïú¿\n„=“\ìœşnu†VN¿p\íg\à;\Ş²hğºÇ˜ñnWñOŠ\Ö\Ø\Ø*\àU£Íƒ\Ë\Ë%’ \Ç*YñY-\\lJÌ¬ñM0:µ^­l\ÙJ][ \ã{_<²1°\Æ.\ç“ôwT\ëÉ¹q;”\Ø\åª\"\ê£\ë_>¨\Ş\ãùı\Z\Ú	g\ë\Ü\Ş\È[\Ù\Ùnõµ\Ò¢®S%¾¦Ñ´\îi\ÜIQÀ®\Ä\å\Ò2u™‡ª•»$Š\ìy\ï \ç\â½TºYsm‘=\"6\ì\ç4cø™\ä6¯5À¢X›GÔ”µ,•’7µ\ìp»\\\Ò\×¼µ]_:j–µO£_ôgd\İ\ÑPñ-û.\æ8w÷X\Öh4„x\âwhz\Ì>³1¼sø„V\éP› ª\ÄÒšN\ZXÌ³\È\Ø\Ø7¸\í<\0\Ú\ã\ÈfW­(\ÃOx\é\0]˜\ïô,>\Ğ\ÎN\æ\å÷—’\é})=lmD‘\Ù\Úù5€ıV4dÑ³f\ÛgtÆµt«,·…¦&l\ë\\‘\Ş\Ãs\Í\ÎT¯;ps\Ü^\â\\\ç¹\Î%\Îq\â\ç\É\æU\Ø\â\ä¯\ÅL÷z­¿=\Ş{š£©\ä?\ã5±‹D\ë¿À\Ï\Éf\ÅF\Ælhñ\Ìø_b\r4Q9Ş«O\ë\Ù\ïXºLº6¸»\'>9n¶ÏŠ\êV*\é›#K^.Ó¶ÿ\0=\ÊO@Tâ»\ËI#o\r\ßğ·HÈ™I9úF¹\\KNN€\ÏqóP«\ÖfŒ¢aq\âr[O¹ü…W¥¢‹\"ëŸ²\Ş\Ññ\Ü<W->šlœ\ão²Ü‡¶ø¬\İ``úHúÇ“`/–y‡y¿(2†–sjx²\ã\ë{-ñYjÌ’U\æœG\Ì\ä<W$\ÖpÁa€\Ø7\0š\à\í\Í_2ƒ¤¢\ÑP\Ã\ê0_\í\İ\ævx,À¸ƒ­S8Ù¢\ç€şj\ëjt„¾¬Rş™g!`±\ß\ÃÃ‡r¤u7‹ \ì=\áq\çF\émm½§´{±(?ATızˆ\íH? V~\æ]]Sµkö±\è\è³sï»ª’¥ƒ\ë4x«2iXÀ\Âd½\í·>K˜¨\Ğ2\ÔzL.clò1\È\ëZ8Às¬q€\0mXtT‘Éœ“\àh.³ˆ¾\í—Xõ^úgq—[‘Ò–8^\äf2>³N&ûÀZj\ê\Ø\Èf\\p¶ı—dX\\Ár@¹,ld\Û}\Ñ\ÔMµ\ä¨<-\Ìl¸\æ<\Â:J 2ı¦€EVJ\á`;n.@\Ü¤\Ö&\Ø3\ï¿Áaõ´¿\Ø\Ì\ïj@>K\Ò\é\Æb”÷¤yü\Ğe3X\É\Ø\Öø\İQú\Ç&\æ´xñ+aªºN‹\Ò\é´Ñ¶\0	%Œ.\íÛŒX—6ùXr\İu«\Óôô\æ¦OCqtØ£%®ihvx,\àd\Üs\ßtşW‘\ç\é\\>\Î\Æø´dV\ÎJ™\ë[#ˆ…›2o’Å \ÑV\Íş[\Ï¸m†AAX\ã\r\0`° *Apª+ *\Ù‚\Å{GWKK#f…\åiÈüˆ\Ş?YŒ‹\n‰b¨÷-F\×Xô‹0:Ì¨h\í3s­õ™Ëˆ\Ú9‹\Ö/˜©\å|/l±8±\í µ\Ã\"ı{ü¹\ê··HÅ…\ÖmCm»†Î±ƒ\Ş7DR\"\"ˆˆ€ˆ¸®u\ê:\ßN\ÅV\æÆ€\"$dùoµÁ\r\Úr\Ê\× 7ZÍ­4\Ú=˜§hƒ‚6\Ø\Èÿ\0e»‡3a\Íx¶·k\ÍN»\\\îª\r\Ñ4\ä\áş+²\ÇİÙ•ó\\m]\\ó½\ÒJ÷½\î7sœs><9l\n\Óiœs¶\\N\Ï3ù \Íô–\r÷\îYšX\çqh6 ^\ÄfFü m·~ı‹Q;Á\ëcw\ä?Ájœù	¸›vfEùı\ÖTz3™B\ïsE·¼´û\0{‚ÇŸY)Yıhw²\ïx^x)¸º÷\ä¦\ÊF*º}uˆz‘H\îü,šÀ“\\\'y´P°_\Ú{½\ÖZF\ÂÑ¹\\°Úƒ:m%Zı²`\ä\Ü->\Íkg†W›¾R\ã\Íä«ª—Aˆ\Úkoò¤Ø€ıYd\\N\ë(¹„\æœÁ\àƒp­©5\ÖA›G\è\ï&ñ¸¸miq\æ\\>^*o\Ò-Š’­›š\éOöKY07nNşJ\ç¤\â\í†^ö¸76sC‡ˆ³¿VA.±\Õƒ„|šÀÏˆXRéŠ‡ú\Ó\Èh‚µ1s\íØ·p9÷’­õı‚™\Çk\Ü{\ÜO\Å[\rŞ«‹›\æ¤!O€(#O}\Øl		6\0=¹‹“Ç´\Ş w(:\"bÄ‹Ÿ=\Æ\ë**bOe¤\'\äÂŸG†\æósÀ|\Ğ`zA¸ \ÎW\ÄrÛ—/z\Çl\ë~\"ˆ}_2U\æ`\ÜAÎ¶˜€Ÿ\×%}š=\ç\êû¾k}‰S\r\\Z(ŸX\ï[\Zzf³`Ï‰Ú§tº˜”ƒ•€T® ¾®4¬`\åq®A’Ò¦–u¥A+%”‚­Z-W4ul”²²x]…\ì7q\â7‚2!,¢\æª>‚\Õ}=|\r™™nö<miøƒ¼¶\ËÀõ#X©$õ/³e·<-¿‘!{\Ó@ ŒÁa\n‰\"\"5°¹ñ½y\Îcš\Û0@x\"A\Ï>ÀOGZH\Ìøú’\âo30>ä³\'_iÈ»<\Âúx\Èèµ”ñõµõ\ÑBÁk‘`\Ñ}\İd„f{–¢“VômLl’Ï€›4ö‡ˆh.f!—š÷-)£bª‰\Ğ\ÌÀö8Xƒñ\nò+Ğ¡c„”cšq4J3vc±o\á(5®\Õ\ZVlŠşÓœ}×²Å›ED\ÏV&\æ…\Ñ\ÑE[\é\nr\×\ìlñ\ÙğK\íÿ\04ó\Ì\0wX\Ø+u´¶Ü \áôp- [‚\åj\éLf\Ç6\î?‘ù¯C®¦\\Ş§\ÚH9’,¢²%±õwŠ‹£TXº¡*\ébbe\ÖQ‘\æù·q¸\ï\Ï#\É],V¥¼o·z\nµ?P~!ù)uû£¼Ÿ’\Ë\Ñ\r\Õa\'e\Å\ÇvVşj*\\6\ße­gğ€ƒA„¶\Ø\ËlN‹€\Ó\Íg·F}ğ;®¶”\ì\ÃÉ‘¤as\\æ¹¦Ä´\çq˜˜ bÂš.¢\Ä8º1\çÖ‰\Ûz©­¿#glp±\r\n&³{\Éıs*¢ŠõI\ï#\ä¯Y,‚-Š1²1\ï*aö\Ø\Ğ<¥•KT\Ì|»ªµª\æİš£J¼Ø¯½E\È\rwG«oª–\Ïb\0\É	Tp*C”®¢…Ã”\ÚU¦Òƒ%Y+\r…IÕŒf\Óåšƒ`\Õ%¯‹I\Æ~µ»ÁYñ¼8\\G,\ĞUUUPY{W¬ôO§ú\ØM,‡·»/¾;\ìı’m\ÜBò²^Ò¦¢9\ÆÆ\×6lpòº£\è„V\é\ælk\ØAkš\Ò6‘pGWD@DD¹\İ`\Ğ!\à¾1öü\Ç\ÉtHƒ\Æô-¯’\åô”\Øõ¯AbX\Æ{\\ñ\Íy†”§µ\Ô5l;V½\Âp›Š\Ş\é––¡ˆ$X¢cP†[dVJ\ÈX\ê\Õz¥x k*(\Ë{Lñ%—G\\3\Ú6¬°Õ‡YA~\Üy;‡Bf†—›X	;ÿ\0C\Íc\ÕW˜ˆ\Í{6»6½„ú¦\Ù\î­6\"\Ä)hF\Æ÷a#»‰n.@ñ\ä¶\çA°›–‚N\Òs\'¾\è4\ÑJ\ØÀ-qtÙ®w¯}T\Öñ³†N\â\Ös[Ÿnka‰kn[g.\É\Í\à}Ç‘\0ŒÀ+S4¥v]\Ğ\ÙvŞ®û\Zò7pP_T·¾\Ê\ã¬v*\0‚\r`T²U$ ˆm\Ğ\0©u?‚	8\ÙP9C\Z¡z	9ÁQ@¹S—T*7UÂ“U¶\æ±++>«Oyü‚••\Øriñù-S\ç%Qîº‚	‰ŠÉ¦­-7i->\â°\Õlƒ¬\Ñú]¯ì¿²\ïqù-¢\à˜J\Ü\è\í2\æ/\Í\Üwšš\"µ(\Élô‡¨®§†G6ö\Æ\æ˜\Ø8öŸ`m\Ê\åzN¬ôu²\Õ8K ±\êšxçœ„s\0r¸º—UAT¡\ÌÀE<#CmE°ı^\í\Ëjˆ¨\"\"\" \"\"\áõ£S!/§Ãx	ÃŸ\İ;-\ße\Ü\"›u‚…ğHc•¥¯ZvÛ1\Ì.j¡«\êú\ê§n	£dû/k^\ß\'\ÇiŠ´|÷,l8\çxŸ—\à“@\ä\Ğ:½ªQ\ÉlŠõ=-ĞµKni\êb_dtDn0\ã\ä¸\í-¨:J\åôr7\ÄÀó´D¸ğ\Z0õ õP\×Dm#\\\ÃÁ\à°ù9[-ûC\Ì \Ø	Ä‹[\éˆóU5#ˆA“WL\Ù3\ØFñ’°[#±\ç\Í\Ç5\ÒjV¤T\ér]›-8]+³\0\ÚøX\Ñ\ë»1•ÀÛ²şŒ:\r§\Ãı2||pÇ‚şÍ¯o\ÚA\âÆŠ^-ñ$ª\ÇG+o…á¸šZ\ì7Í§h#x\äwÁwº\ËÑ½m/oÿ\0¦µñ5\İcy¾“‡›K­½p²U8n;7r;\Ğg°†€\ïzVU8¹\Ö\Ås\Å\ê%\ê\Õ\Õ.‚eÊ˜”n©tº]QV\éuV‹\äJ\êM\\\ä8şC‰T¸w\ç\Ùo´\íşko£µ^¶±˜\éé¥”“\ì\Ø\ã±ş\Í\Òµ\Ã\Ù%;QYŠ\ínM\ŞwŸ\×†\âºWj”½½oÜ·+)3£*vP\É\â\è‡\Å\è9T]¤=iw\è˜yºX-\îy>å³§\è[I»\Ö4\Ìö¥yş\Ê8RzıA›uµ\ÌoÈœ\ï\'9\ã\àº]Ğ®9_<Üœğ\Æøu`;÷x%<8ˆH™9\0ªj/GS\ÌZù˜\èb\ÚKÅ¤p\à\Æ\Û\í8\r¹]z\æ†Õª:?\èô\ÑFmlA£‡´|J\Û ³IJ\ÈX\Ø\ãhk\Z,\0\Ü?>õyNÖƒ\à\Ñ;¨b;ba\ïk~J\Ñ\Ñ\ç?G‹ü¶|–j ‹\Z,\0\0l <‘­\İRW\âxL\ç>±€\çq–=\ï\É\ßx.\Å|ñ¥z)\Ò0\Å<u\æ\Æ)\Ã2\Ü\\Ù…Á\ä	Zyu:½¶¬¿\İÁ ğ\Â\nú}|¬\í]¬ô\í#şK\Ï\Â5«õ¿\İ\ÚGü©Gşõb ùGù·û³H~	ÿ\0ô¡\ĞU¿\İu¾1ÔŸ„a}\\ˆ>N:·û®«\Æ\Z¿\Ê\ÊBÖú\\\Ş0V¹}hˆ>P‡Vô„¦\ÌÑ“À£\Å\Ò#\ÅwZ½\Ñ6™¶«´\Ñ	¶‘\î½C{‰s­Á{ª \äµ{£­Eb\Øz\ÙY7\Ò8½­¶mh+­D@DDD@DDD@DDD@DDD@DDD@DDD@DDD@DDD@DDD@DDD@DDD@DDD@DDD@DDDAÿ\Ù','2021-02-26');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question`
--

DROP TABLE IF EXISTS `question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `question` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `TYPE` varchar(255) DEFAULT NULL,
  `QUESTIONNAIRE_ID` int DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_QUESTION_QUESTIONNAIRE_ID` (`QUESTIONNAIRE_ID`),
  CONSTRAINT `FK_QUESTION_QUESTIONNAIRE_ID` FOREIGN KEY (`QUESTIONNAIRE_ID`) REFERENCES `questionnaire` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question`
--

LOCK TABLES `question` WRITE;
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
INSERT INTO `question` VALUES (1,'My expertise level is.. [Low, Medium, High]','FIXED',1),(2,'Question 2','MARKETING',1),(3,'Question 1','MARKETING',1),(4,'I identify my gender as..','FIXED',1),(5,'My age is..','FIXED',1),(6,'Question 1','MARKETING',2),(7,'My expertise level is.. [Low, Medium, High]','FIXED',2),(8,'Question 2','MARKETING',2),(9,'Question 3','MARKETING',2),(10,'I identify my gender as..','FIXED',2),(11,'My age is..','FIXED',2);
/*!40000 ALTER TABLE `question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `questionnaire`
--

DROP TABLE IF EXISTS `questionnaire`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `questionnaire` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `RELATEDPRODUCT_ID` int DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `RELATEDPRODUCT_ID` (`RELATEDPRODUCT_ID`),
  CONSTRAINT `FK_QUESTIONNAIRE_RELATEDPRODUCT_ID` FOREIGN KEY (`RELATEDPRODUCT_ID`) REFERENCES `product` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `questionnaire`
--

LOCK TABLES `questionnaire` WRITE;
/*!40000 ALTER TABLE `questionnaire` DISABLE KEYS */;
INSERT INTO `questionnaire` VALUES (1,1),(2,2);
/*!40000 ALTER TABLE `questionnaire` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `review`
--

DROP TABLE IF EXISTS `review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `review` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `DATE` date DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `PRODUCT_ID` int DEFAULT NULL,
  `USER_ID` int DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `USER_ID` (`USER_ID`),
  KEY `FK_REVIEW_PRODUCT_ID` (`PRODUCT_ID`),
  CONSTRAINT `FK_REVIEW_PRODUCT_ID` FOREIGN KEY (`PRODUCT_ID`) REFERENCES `product` (`ID`),
  CONSTRAINT `FK_REVIEW_USER_ID` FOREIGN KEY (`USER_ID`) REFERENCES `user` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
INSERT INTO `review` VALUES (1,'2021-02-26','I love this product!',1,2),(2,'2021-02-26','I don\'t like it so much.',1,3);
/*!40000 ALTER TABLE `review` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sequence`
--

DROP TABLE IF EXISTS `sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sequence` (
  `SEQ_NAME` varchar(50) NOT NULL,
  `SEQ_COUNT` decimal(38,0) DEFAULT NULL,
  PRIMARY KEY (`SEQ_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sequence`
--

LOCK TABLES `sequence` WRITE;
/*!40000 ALTER TABLE `sequence` DISABLE KEYS */;
INSERT INTO `sequence` VALUES ('SEQ_GEN',50);
/*!40000 ALTER TABLE `sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `EMAIL` varchar(255) DEFAULT NULL,
  `IS_BLOCKED` tinyint(1) DEFAULT '0',
  `LAST_LOGIN` datetime(3) DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `SURNAME` varchar(255) DEFAULT NULL,
  `USERNAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `EMAIL` (`EMAIL`),
  UNIQUE KEY `USERNAME` (`USERNAME`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'user1@gmail.com',0,'2021-02-26 01:32:07.659','User1Name','user1pass','User1Surname','user1'),(2,'user2@gmail.com',0,'2021-02-26 01:28:35.228','User2Name','user2pass','User2Surname','user2'),(3,'user3@gmail.com',0,'2021-02-26 01:30:02.854','User3Name','user3pass','User3Surname','user3'),(4,'user4@gmail.com',0,'2021-02-26 01:32:26.743','User4Name','user4pass','User4Surname','user4');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `useranswer`
--

DROP TABLE IF EXISTS `useranswer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `useranswer` (
  `ID` bigint NOT NULL,
  `POINTSEARNED` int DEFAULT NULL,
  `STATUS` varchar(255) DEFAULT NULL,
  `RELATEDQUESTIONNAIRE_ID` int DEFAULT NULL,
  `RELATEDUSER_ID` int DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_USERANSWER_RELATEDQUESTIONNAIRE_ID` (`RELATEDQUESTIONNAIRE_ID`),
  KEY `FK_USERANSWER_RELATEDUSER_ID` (`RELATEDUSER_ID`),
  CONSTRAINT `FK_USERANSWER_RELATEDQUESTIONNAIRE_ID` FOREIGN KEY (`RELATEDQUESTIONNAIRE_ID`) REFERENCES `questionnaire` (`ID`),
  CONSTRAINT `FK_USERANSWER_RELATEDUSER_ID` FOREIGN KEY (`RELATEDUSER_ID`) REFERENCES `user` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `useranswer`
--

LOCK TABLES `useranswer` WRITE;
/*!40000 ALTER TABLE `useranswer` DISABLE KEYS */;
INSERT INTO `useranswer` VALUES (1,8,'SUBMITTED',1,1),(2,0,'CANCELLED',1,2),(3,16,'SUBMITTED',1,3),(4,4,'SUBMITTED',1,4);
/*!40000 ALTER TABLE `useranswer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `useranswer_answers`
--

DROP TABLE IF EXISTS `useranswer_answers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `useranswer_answers` (
  `UserAnswer_ID` bigint DEFAULT NULL,
  `ANSWERS` varchar(255) DEFAULT NULL,
  `answers_KEY` int DEFAULT NULL,
  KEY `FK_UserAnswer_ANSWERS_answers_KEY` (`answers_KEY`),
  KEY `FK_UserAnswer_ANSWERS_UserAnswer_ID` (`UserAnswer_ID`),
  CONSTRAINT `FK_UserAnswer_ANSWERS_answers_KEY` FOREIGN KEY (`answers_KEY`) REFERENCES `question` (`ID`),
  CONSTRAINT `FK_UserAnswer_ANSWERS_UserAnswer_ID` FOREIGN KEY (`UserAnswer_ID`) REFERENCES `useranswer` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `useranswer_answers`
--

LOCK TABLES `useranswer_answers` WRITE;
/*!40000 ALTER TABLE `useranswer_answers` DISABLE KEYS */;
INSERT INTO `useranswer_answers` VALUES (1,'High',1),(1,'Ans 1',2),(1,'Ans 2',3),(3,'High',1),(3,'Answer 1',2),(3,'Male',4),(3,'23',5),(3,'Answer 2',3),(4,'anal',2),(4,'Ans 2',3);
/*!40000 ALTER TABLE `useranswer_answers` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `useranswer_answers_AFTER_INSERT` AFTER INSERT ON `useranswer_answers` FOR EACH ROW BEGIN
	IF( EXISTS (SELECT *
		FROM QUESTION
		WHERE new.answers_KEY = ID AND TYPE = 'FIXED'))
	THEN	
		UPDATE USERANSWER
		SET POINTSEARNED = POINTSEARNED + 2
		WHERE ID = new.UserAnswer_ID;
	ELSE 
		UPDATE USERANSWER
		SET POINTSEARNED = POINTSEARNED + 1
		WHERE ID = new.UserAnswer_ID;
	END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-02-26  1:36:21
