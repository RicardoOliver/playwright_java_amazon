package com.amazon.qa.utils;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class TestDataGenerator {
    private static final Random random = new Random();
    
    private static final List<String> PRODUCT_SEARCH_TERMS = Arrays.asList(
        "celular", "notebook", "fone de ouvido", "smartwatch", "tablet",
        "livro", "kindle", "camera", "tv", "monitor", "mouse", "teclado",
        "cadeira gamer", "headset", "console", "playstation", "xbox"
    );
    
    private static final List<String> BRAND_SEARCH_TERMS = Arrays.asList(
        "samsung", "apple", "xiaomi", "motorola", "lg", "sony", "dell",
        "hp", "lenovo", "acer", "asus", "jbl", "philips", "logitech"
    );
    
    private static final List<String> SPECIAL_SEARCH_TERMS = Arrays.asList(
        "promoção", "oferta do dia", "mais vendidos", "frete grátis",
        "prime", "desconto", "liquidação", "black friday"
    );
    
    private static final List<String> INVALID_SEARCH_TERMS = Arrays.asList(
        "ççççççççç", "!@#$%^&*()", "テスト", "测试", "테스트",
        "a".repeat(256), "", "   "
    );
    
    public static String getRandomProductSearchTerm() {
        return PRODUCT_SEARCH_TERMS.get(random.nextInt(PRODUCT_SEARCH_TERMS.size()));
    }
    
    public static String getRandomBrandSearchTerm() {
        return BRAND_SEARCH_TERMS.get(random.nextInt(BRAND_SEARCH_TERMS.size()));
    }
    
    public static String getRandomSpecialSearchTerm() {
        return SPECIAL_SEARCH_TERMS.get(random.nextInt(SPECIAL_SEARCH_TERMS.size()));
    }
    
    public static String getRandomInvalidSearchTerm() {
        return INVALID_SEARCH_TERMS.get(random.nextInt(INVALID_SEARCH_TERMS.size()));
    }
    
    public static String getRandomSearchTerm() {
        int type = random.nextInt(3);
        switch (type) {
            case 0:
                return getRandomProductSearchTerm();
            case 1:
                return getRandomBrandSearchTerm();
            default:
                return getRandomSpecialSearchTerm();
        }
    }
    
    public static String getCombinedSearchTerm() {
        return getRandomBrandSearchTerm() + " " + getRandomProductSearchTerm();
    }
}
