package insights;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import com.ibm.watson.developer_cloud.personality_insights.v3.PersonalityInsights;

import com.ibm.watson.developer_cloud.personality_insights.v3.model.Profile;

public class Insights_main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		PersonalityInsights service = new PersonalityInsights("2017-10-13");
		service.setUsernameAndPassword("naisyo", "naisyo");
		
		Insight_lib ilib = new Insight_lib();
		MySQL mysql = new MySQL();
		ResultSet rs = mysql.getID();
		try {
			while(rs.next()){
			    int id = rs.getInt("id"); 
				System.out.println("id : "+id);
				MySQL mysql1 = new MySQL();
				ResultSet rs1 = mysql1.getContent(id);
				StringBuilder buf = new StringBuilder();
				while(rs1.next()) {
					buf.append(rs1.getString("comment"));
				}
				String text = buf.toString();
				System.out.println("Comment : "+text);
				Profile profile = ilib.getInsightbig5(text);
				System.out.println(profile);
				double result[][] = ilib.getJson(String.valueOf(profile));
				System.out.println("Personality Insight : "+ Arrays.deepToString(result));
				mysql1.updateImage(result, id);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Openness(知的好奇心)属性");
		System.out.println("Adventurousness(大胆性), Artistic interests(芸術的関心度), Emotionality(情動性), Imagination(想像力), Intellect(思考力), Authority-challenging(現状打破)");
		System.out.println("Conscientiousness(誠実性)属性");
		System.out.println("Achievement striving(達成努力), Cautiousness(注意深さ), Dutifulness(忠実さ), Orderliness(秩序性), Self-discipline(自制力), Self-efficacy(自己効力感)");
		System.out.println("Extraversion(外向性)属性");
		System.out.println("Activity level(活発度), Assertiveness(自己主張), Cheerfulness(明朗性), Excitement-seeking(刺激希求性), Outgoing(友好性), Gregariousness(社交性)");
		System.out.println("Agreeableness(協調性)属性");
		System.out.println("Altruism(利他主義), Cooperation(協働性), Modesty(謙虚さ), Uncompromising(強硬さ), Sympathy(共感度), Trust(信用度)");
		System.out.println("Neuroticism(感情起伏)属性");
		System.out.println("Fiery(激情的), Prone to worry(心配性), Melancholy(悲観的), Immoderation(利己的), Self-consciousness(自意識過剰), Susceptible to stress(低ストレス耐性)");
					
	}

}
