package ogr.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                // 指定构建api文档的详细信息方法：apiInfo()
                .apiInfo(apiInfo())
                .select()
//                .apis(RequestHandlerSelectors.any())
                // 指定要生成api接口的包路径，不要使用通配符 例如： com.demo.*
                .apis(RequestHandlerSelectors.basePackage("ogr.spring"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {

        return new ApiInfoBuilder()
            .title("boot_cloud")
            .description("从boot到cloud")
            .version("1.0")
            .build();
    }

}
