package SGABSHACKALB.Gestion.des.Reservations;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class GestionDesReservationsApplication {

	/*public static final String dateFormat = "yyyy-MM-dd";
	public static final String dateTimeFormat = "yyyy-MM-dd HH:mm:ss";*/

    public static void main(String[] args) {
        SpringApplication.run(GestionDesReservationsApplication.class, args);
    }

	/*@Bean
	public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer(){
		return builder -> {
			builder.simpleDateFormat(dateFormat);
			builder.serializers(new LocalDateSerializer(DateTimeFormatter.ofPattern(dateFormat)));
			builder.serializers(new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(dateTimeFormat)));
		};
	}*/

}
