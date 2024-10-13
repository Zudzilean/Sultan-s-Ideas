import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class CardDeck {
    public static void main(String[] args) {
        // Card IDs
        String[] cardIDs = {
                "2010001", // 岩石杀戮卡
                "2010002", // 青铜杀戮卡
                "2010003", // 白银杀戮卡
                "2010004", // 黄金杀戮卡
                "2010005", // 岩石纵欲卡
                "2010006", // 青铜纵欲卡
                "2010007", // 白银纵欲卡
                "2010008", // 黄金纵欲卡
                "2010009", // 岩石奢靡卡
                "2010010", // 青铜奢靡卡
                "2010011", // 白银奢靡卡
                "2010012", // 黄金奢靡卡
                "2010013", // 岩石征服卡
                "2010014", // 青铜征服卡
                "2010015", // 白银征服卡
                "2010016"  // 黄金征服卡
        };

        // Card Descriptions
        String[] cardDescriptions = {
                "一张岩石杀戮卡！",
                "一张青铜杀戮卡！",
                "一张白银杀戮卡！",
                "一张黄金杀戮卡！",
                "一张岩石纵欲卡！",
                "一张青铜纵欲卡！",
                "一张白银纵欲卡！",
                "一张黄金纵欲卡！",
                "一张岩石奢靡卡！",
                "一张青铜奢靡卡！",
                "一张白银奢靡卡！",
                "一张黄金奢靡卡！",
                "一张岩石征服卡！",
                "一张青铜征服卡！",
                "一张白银征服卡！",
                "一张黄金征服卡！"
        };

        Scanner scanner = new Scanner(System.in);
        Map<String, Integer> cardCounts = new LinkedHashMap<>(); // Use LinkedHashMap to maintain order

        for (int i = 0; i < cardIDs.length; i++) {
            System.out.print("请输入您想要的 " + cardDescriptions[i] + " 数量 (输入 0 表示不需要此卡): ");
            int quantity;
            try {
                quantity = Integer.parseInt(scanner.nextLine());

                if (quantity < 0) {
                    System.out.println("数量不能为负数，请重新输入。");
                    continue;
                }

                cardCounts.put(cardIDs[i], quantity);
            } catch (NumberFormatException e) {
                System.out.println("输入无效，请输入一个整数。");
            }
        }

        // Generate sudan_pool and sudan_pool_desc JSON output
        StringBuilder sudanPool = new StringBuilder();
        StringBuilder sudanPoolDesc = new StringBuilder();
        sudanPool.append("[\n");
        sudanPoolDesc.append("[\n");

        for (Map.Entry<String, Integer> entry : cardCounts.entrySet()) {
            String cardID = entry.getKey();
            int count = entry.getValue();
            for (int i = 0; i < count; i++) {
                sudanPool.append("    ").append(cardID).append(",\n");
                int index = Integer.parseInt(cardID) - 2010001; // Convert ID back to index
                sudanPoolDesc.append("    \"").append(cardDescriptions[index]).append("\",\n");
            }
        }

        // Remove the last comma and add closing brackets
        if (sudanPool.length() > 2) {
            sudanPool.setLength(sudanPool.length() - 2); // Remove last comma
        }
        if (sudanPoolDesc.length() > 2) {
            sudanPoolDesc.setLength(sudanPoolDesc.length() - 2); // Remove last comma
        }

        sudanPool.append("\n]");
        sudanPoolDesc.append("\n]");

        // Print results
        System.out.println("\n生成的 sudan_pool:");
        System.out.println(sudanPool);
        System.out.println("\n生成的 sudan_pool_desc:");
        System.out.println(sudanPoolDesc);
    }
}
