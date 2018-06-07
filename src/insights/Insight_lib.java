package insights;

import java.io.IOException;
import java.util.Arrays;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.watson.developer_cloud.personality_insights.v3.PersonalityInsights;
import com.ibm.watson.developer_cloud.personality_insights.v3.model.Profile;
import com.ibm.watson.developer_cloud.personality_insights.v3.model.ProfileOptions;
import com.ibm.watson.developer_cloud.personality_insights.v3.model.ContentItem.Language;

public class Insight_lib {
	
	PersonalityInsights service;

	public Insight_lib() {
		service = new PersonalityInsights("2017-10-13");
		this.service.setUsernameAndPassword("d77602b6-6d14-4c8c-8b77-bf51fb6bdfae", "s11qCvf4PKcR");	
	}
	
	public Profile getInsightbig5(String comment){
		Profile profile;
		ProfileOptions options = new ProfileOptions.Builder()
				.contentLanguage(Language.JA)
				//.acceptLanguage(Language.JA)
		        .text(comment)
		        .build();
		profile = service.profile(options).execute();
		return profile;
	}

	public double[][] getJson(String s){
		double result[][] = new double[5][7];
		ObjectMapper mapper = new ObjectMapper();
        try {
			JsonNode node = mapper.readTree(s);
			for(int i = 0; i < result.length; i++) {
				result[i][0] = node.get("personality").get(i).get("percentile").asDouble();
			}
			//System.out.println("Openness(知的好奇心), Conscientiousness(誠実性), Extraversion(外向性), Agreeableness(協調性), Neuroticism(感情起伏)" + Arrays.toString(big5));
			
			for(int i = 0; i < result.length; i ++) {
				for(int j = 0; j < result[0].length-1; j++) {
					result[i][j+1] = node.get("personality").get(i).get("children").get(j).get("percentile").asDouble();
				}
			}
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
