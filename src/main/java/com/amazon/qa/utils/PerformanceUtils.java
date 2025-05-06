package com.amazon.qa.utils;

import com.microsoft.playwright.Page;
import java.util.Map;
import java.util.HashMap;

public class PerformanceUtils {
    
    public static Map<String, Object> getPerformanceMetrics(Page page) {
        Map<String, Object> metrics = new HashMap<>();
        
        Object timing = page.evaluate("() => {" +
            "const perfData = window.performance.timing;" +
            "return {" +
            "  navigationStart: perfData.navigationStart," +
            "  redirectStart: perfData.redirectStart," +
            "  redirectEnd: perfData.redirectEnd," +
            "  fetchStart: perfData.fetchStart," +
            "  domainLookupStart: perfData.domainLookupStart," +
            "  domainLookupEnd: perfData.domainLookupEnd," +
            "  connectStart: perfData.connectStart," +
            "  connectEnd: perfData.connectEnd," +
            "  secureConnectionStart: perfData.secureConnectionStart," +
            "  requestStart: perfData.requestStart," +
            "  responseStart: perfData.responseStart," +
            "  responseEnd: perfData.responseEnd," +
            "  domLoading: perfData.domLoading," +
            "  domInteractive: perfData.domInteractive," +
            "  domContentLoadedEventStart: perfData.domContentLoadedEventStart," +
            "  domContentLoadedEventEnd: perfData.domContentLoadedEventEnd," +
            "  domComplete: perfData.domComplete," +
            "  loadEventStart: perfData.loadEventStart," +
            "  loadEventEnd: perfData.loadEventEnd" +
            "};" +
            "}");
        
        if (timing instanceof Map) {
            @SuppressWarnings("unchecked")
            Map<String, Object> timingMap = (Map<String, Object>) timing;
            
            // Calculate key metrics
            long navigationStart = ((Number) timingMap.get("navigationStart")).longValue();
            long responseStart = ((Number) timingMap.get("responseStart")).longValue();
            long responseEnd = ((Number) timingMap.get("responseEnd")).longValue();
            long domComplete = ((Number) timingMap.get("domComplete")).longValue();
            long loadEventEnd = ((Number) timingMap.get("loadEventEnd")).longValue();
            
            // Add calculated metrics
            metrics.put("ttfb", (responseStart - navigationStart) / 1000.0); // Time to First Byte in seconds
            metrics.put("responseTime", (responseEnd - responseStart) / 1000.0); // Response download time in seconds
            metrics.put("domProcessingTime", (domComplete - responseEnd) / 1000.0); // DOM processing time in seconds
            metrics.put("totalLoadTime", (loadEventEnd - navigationStart) / 1000.0); // Total page load time in seconds
            
            // Add raw metrics
            metrics.put("rawMetrics", timingMap);
        }
        
        return metrics;
    }
    
    public static Map<String, Object> getResourceMetrics(Page page) {
        Object resources = page.evaluate("() => {" +
            "const resources = performance.getEntriesByType('resource');" +
            "const result = {" +
            "  totalResources: resources.length," +
            "  totalSize: 0," +
            "  resourcesByType: {}" +
            "};" +
            "resources.forEach(resource => {" +
            "  const type = resource.initiatorType || 'other';" +
            "  if (!result.resourcesByType[type]) {" +
            "    result.resourcesByType[type] = {" +
            "      count: 0," +
            "      size: 0" +
            "    };" +
            "  }" +
            "  result.resourcesByType[type].count++;" +
            "  if (resource.transferSize) {" +
            "    result.totalSize += resource.transferSize;" +
            "    result.resourcesByType[type].size += resource.transferSize;" +
            "  }" +
            "});" +
            "return result;" +
            "}");
        
        @SuppressWarnings("unchecked")
        Map<String, Object> resourceMetrics = (Map<String, Object>) resources;
        return resourceMetrics;
    }
}
