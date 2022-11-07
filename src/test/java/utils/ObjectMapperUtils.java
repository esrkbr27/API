package utils;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;


    public class ObjectMapperUtils {
        //new ObjectMapper().readValue(jsonInString, HashMap .class);
        //Object Mapperı her classta yeni bir obje üretmememk ve her seferinde exception fırlatmaması
        //için bu objeyı metodun içine koyacagız



        private static ObjectMapper mapper;
        static {
            mapper = new ObjectMapper();
        }
        public static <T> T convertJsonToJava(String json, Class<T> cls){//Generic Method
            T javaResult = null;
            try {
                javaResult = mapper.readValue(json,cls);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return javaResult;
        }

}
