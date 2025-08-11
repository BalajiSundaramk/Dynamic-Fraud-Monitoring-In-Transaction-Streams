package com.example.fraudchecker;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PageController {

    @GetMapping("/trancheck")
    public String getPage() {
        return "trancheck"; // This refers to the "trancheck.html" inside src/main/resources/templates/
    }

    @PostMapping("/analyze")
    public String analyzeTransaction(
            @RequestParam int amount,
            @RequestParam String location,
            @RequestParam String device,
            @RequestParam String merchant,
            Model model) {

        String currentTime = new SimpleDateFormat("HH:mm:ss").format(new Date());

        int riskScore = 0;
        String riskLevel = "LOW RISK";
        String recommendation = "Approve transaction";
        StringBuilder riskFactors = new StringBuilder();

        // Amount-based risk assessment
        if (amount > 50000) {
            riskScore += 30;
            riskFactors.append("High amount (₹").append(amount).append(") - Above normal threshold\n");
        } else if (amount > 20000) {
            riskScore += 15;
            riskFactors.append("Moderate amount (₹").append(amount).append(") - Requires additional verification\n");
        } else {
            riskScore += 5;
            riskFactors.append("Normal transaction amount (₹").append(amount).append(")\n");
        }

        // Location-based risk assessment
        switch (location) {
            case "international" -> {
                riskScore += 25;
                riskFactors.append("International transaction - Higher fraud probability\n");
            }
            case "high_risk" -> {
                riskScore += 35;
                riskFactors.append("High-risk location detected\n");
            }
            case "other_india" -> {
                riskScore += 10;
                riskFactors.append("Different Indian city than usual\n");
            }
            default -> {
                riskScore += 2;
                riskFactors.append("Transaction from usual location\n");
            }
        }

        // Device-based risk assessment
        switch (device) {
            case "vpn" -> {
                riskScore += 30;
                riskFactors.append("VPN/Tor usage detected - Common in fraudulent transactions\n");
            }
            case "new_diff_city" -> {
                riskScore += 25;
                riskFactors.append("New device from different city\n");
            }
            case "new_but_same_city" -> {
                riskScore += 15;
                riskFactors.append("New device but same city\n");
            }
            default -> {
                riskScore += 3;
                riskFactors.append("Recognized device\n");
            }
        }

        // Merchant-based risk assessment
        switch (merchant) {
            case "high_risk" -> {
                riskScore += 40;
                riskFactors.append("High-risk merchant category\n");
            }
            case "new_unknown" -> {
                riskScore += 25;
                riskFactors.append("First transaction with unknown merchant\n");
            }
            case "new_trusted" -> {
                riskScore += 10;
                riskFactors.append("New merchant in trusted category\n");
            }
            default -> {
                riskScore += 5;
                riskFactors.append("Trusted merchant\n");
            }
        }

        // Time-based risk assessment
        int hour = Integer.parseInt(currentTime.split(":")[0]);
        if (hour >= 2 && hour <= 5) {
            riskScore += 20;
            riskFactors.append("Unusual transaction time (2AM-5AM)\n");
        } else {
            riskScore += 2;
            riskFactors.append("Normal transaction time\n");
        }

        // Limit the risk score to 100
        riskScore = Math.min(100, riskScore);

        // Final risk level and recommendation
        if (riskScore >= 70) {
            riskLevel = "HIGH RISK";
            recommendation = "Recommendation: Block transaction and flag account for review";
        } else if (riskScore >= 40) {
            riskLevel = "MEDIUM RISK";
            recommendation = "Recommendation: Require additional authentication (OTP/Biometric)";
        }

        // Add attributes to the model for rendering in the result page
        model.addAttribute("amount", amount);
        model.addAttribute("riskScore", riskScore);
        model.addAttribute("riskLevel", riskLevel);
        model.addAttribute("riskFactors", riskFactors.toString());
        model.addAttribute("recommendation", recommendation);
        model.addAttribute("currentTime", currentTime);

        return "result"; // Refers to "result.html" inside src/main/resources/templates/
    }
}
