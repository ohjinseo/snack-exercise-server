package com.soma.snackexercise.config;

// 스프링 서버 전역적으로 CORS 설정
//@Configuration
//public class WebConfig implements WebMvcConfigurer {
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**") // CORS 정책을 적용할 URL 패턴
//                .allowedOrigins("http://localhost:3000") // 허용할 출처
//                .allowedMethods("POST", "GET", "PUT", "PATCH", "DELETE", "OPTIONS") // 허용할 HTTP method
//                // .allowCredentials(true) // 쿠키 인증 요청 허용
//                .allowedHeaders("Content-Type")
//                .maxAge(3000); // 원하는 시간만큼 pre-flight 리퀘스트를 캐싱
//    }
//}
