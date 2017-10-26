package com.touna.leeo.dailytest;

import java.io.IOException;

public class Test {

	public static void main(String[] args) throws IOException {
		
		String jujueStr = "BD01,B001,B002,B003,B004,B048,B007,B043,B038,B046,B061,B022,B071,B077,B051,B062,B050,B049,B080,B047,B064,B065,B045,B066,B079,B081,B069,B070,B008,B027,B057,B023,B087,B024,B009,B005,B035, B020,B037,B025,B039,B029,B040,B042,B044,B028,B032,B033,B017,B018,B034,B019,,B015,B060,B056,B055,B054,B052,B084,B074,B067,B086,B082,B072,B075,B085,B076,B059,B030B012,B010,B013,B014,B116,B088,B100,B099,ST01,ST02,ST03,ST04,ST06,ST08,ST09,ST10,FX04,BJ07,BJ26,BJ32,BJ16,BJ21,BJ14,BJ17,BJ30,BJ29,BJ19,BJ02,FX03,BJ34,BJ04,BJ05,BJ06,BJ15,BJ12,BJ31,BJ27,BJ36,BJ20,BJ35,BJ09,BJ22,BJ24,BJ25,BJ11,BJ37,BJ38,BJ10,JX10,JX01,JX04,JX15,JX18,,JX19,JX07,JX08,JX09,JX12,JX05,JX16,JX17,JX02,PA01,ZX01,TX01,TD03,TD01,TD02,XT03,XT05,TX25,XT19,XT20,XT04,XT06,XT18,XT21,XT10,XT14,XT24,XT02,XT11,XT23,XT12,XT13,YX06,YX02,YX03,YX01,YX07,YX04,ZM01,ZC01,ZA01,XT26,ST11,ST12";
//		
		StringBuffer jujueBuffer = new StringBuffer("INSERT INTO tb_quota_rule_dic (id, rule_code,rule_type,rule_desc,insert_time,update_time) VALUES ");
		String[] w = jujueStr.split(",");
		for (String string : w) {
			jujueBuffer.append(" (null,'").append(string.trim()).append("','2',null,now(),now()),");
		}
		System.out.println(jujueBuffer.toString());
		
		
		String rg = "B006,B011,B016,B021,B026,B031,B036,B041,B048,B053,B058,B063,B068,B071,B073,B078,B083,BJ01,BJ03,BJ08,BJ13,BJ18,BJ23,BJ28,BJ33,FX01,JX11,JX03,JX06,JX20,XT22,XT15,YX05,XT16,XT17,XT07,XT08,B089,B090,B091,B092,B093,B094,B095,B096,B097,B098,B100,B101,B102,B103,B104,B105,B106,B107,B108,B109,B110,B111,B112,B113,B114,B115,B117,B118,B119,B120,B121,B122,B123,B124";
		StringBuffer rgBuffer = new StringBuffer("INSERT INTO tb_quota_rule_dic (id, rule_code,rule_type,rule_desc,insert_time,update_time) VALUES ");
		w = rg.split(",");
		for (String string : w) {
			rgBuffer.append(" (null,'").append(string.trim()).append("','3',null,now(),now()),");
		}
		System.out.println(rgBuffer.toString());
		
		String tg = "FX02,JX13";
		StringBuffer tgBuffer = new StringBuffer("INSERT INTO tb_quota_rule_dic (id, rule_code,rule_type,rule_desc,insert_time,update_time) VALUES ");
		w = tg.split(",");
		for (String string : w) {
			tgBuffer.append(" (null,'").append(string.trim()).append("','1',null,now(),now()),");
		}
		System.out.println(tgBuffer.toString());
		
	}

	
	
	
}
