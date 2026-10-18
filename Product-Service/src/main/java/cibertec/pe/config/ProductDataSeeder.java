package cibertec.pe.config;

import cibertec.pe.entity.Product;
import cibertec.pe.repository.IProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductDataSeeder implements CommandLineRunner {

    private final IProductRepository productRepository;

    public ProductDataSeeder(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) {
        if (productRepository.count() > 0) {
            System.out.println(">> [Product-Service] Catálogo existente en MySQL: " + productRepository.count() + " productos.");
            return;
        }

        System.out.println(">> [Product-Service] BD vacía: sembrando catálogo oficial de Nexus Tech Store...");

        List<Product> seed = List.of(
            // Laptops Gamer & Pro
            crear("Laptop ASUS ROG Zephyrus G16 (2026)", "ROG Zephyrus G16", "laptops", "Laptops Gamer & Pro", "ASUS",
                  8999.00, 9599.00, "https://images.unsplash.com/photo-1603302576837-37561b2e2302?auto=format&fit=crop&w=600&q=80",
                  "CyberTech Top", "amber", 12, "ASUS-ROG-G16-2026",
                  "Intel Core Ultra 9 185H • RTX 4080 12GB • 32GB LPDDR5X • 1TB SSD NVMe Gen4 • Pantalla OLED 2.5K 240Hz", 4.9, 38),

            crear("Lenovo Legion Pro 7i Gen 9", "Legion Pro 7i", "laptops", "Laptops Gamer & Pro", "Lenovo",
                  7499.00, 8299.00, "https://images.unsplash.com/photo-1588872657578-7efd1f1555ed?auto=format&fit=crop&w=600&q=80",
                  "Oferta Relámpago", "rose", 8, "LNV-LEG-7I-G9",
                  "Intel Core i9-14900HX • RTX 4070 8GB • 32GB DDR5 5600MHz • 1TB SSD • WQXGA 240Hz HDR400", 4.8, 24),

            crear("MacBook Pro 16\" M3 Max", "MacBook Pro 16", "laptops", "Laptops Gamer & Pro", "Apple",
                  12899.00, 13499.00, "https://images.unsplash.com/photo-1517336714731-489689fd1ca8?auto=format&fit=crop&w=600&q=80",
                  "Gama Alta", "indigo", 5, "APL-MBP-16-M3MAX",
                  "Chip Apple M3 Max (16 CPU, 40 GPU) • 48GB Memoria Unificada • 1TB SSD • Liquid Retina XDR", 5.0, 42),

            // Monitores & Displays
            crear("Monitor Samsung Odyssey OLED G9 49\"", "Odyssey OLED G9", "monitores", "Monitores & Displays", "Samsung",
                  5899.00, 6499.00, "https://images.unsplash.com/photo-1527443224154-c4a3942d3acf?auto=format&fit=crop&w=600&q=80",
                  "Curvo 49\"", "indigo", 6, "SAM-ODY-G9-OLED",
                  "49\" Dual QHD (5120x1440) • 240Hz 0.03ms • Curvatura 1800R • HDR True Black 400 • Smart TV Tizen", 4.9, 19),

            crear("Monitor LG UltraGear 27\" Nano IPS QHD", "LG UltraGear 27\"", "monitores", "Monitores & Displays", "LG",
                  1399.00, 1699.00, "https://images.unsplash.com/photo-1585792180666-f7347c490ee2?auto=format&fit=crop&w=600&q=80",
                  "Más Vendido", "emerald", 25, "LG-UG-27GP850",
                  "27\" QHD (2560x1440) • 180Hz OC 1ms Nano IPS • G-Sync Compatible & FreeSync Premium • HDR400", 4.7, 85),

            // Tarjetas de Video & CPUs
            crear("Tarjeta Gráfica ASUS TUF Gaming RTX 4080 Super 16GB", "ASUS TUF RTX 4080S", "componentes", "Tarjetas de Video & CPUs", "ASUS",
                  4699.00, 4999.00, "https://images.unsplash.com/photo-1587202372775-e229f172b9d7?auto=format&fit=crop&w=600&q=80",
                  "Stock Limitado", "rose", 7, "ASUS-TUF-4080S-16G",
                  "16GB GDDR6X 256-bit • Arquitectura Ada Lovelace • Dual BIOS • Diseño 3.65 ranuras con Axial-tech", 4.9, 31),

            crear("Procesador AMD Ryzen 7 7800X3D", "Ryzen 7 7800X3D", "componentes", "Tarjetas de Video & CPUs", "AMD",
                  1899.00, 2199.00, "https://images.unsplash.com/photo-1555680202-c86f0e12f086?auto=format&fit=crop&w=600&q=80",
                  "El Rey del Gaming", "amber", 18, "AMD-RYZ-7800X3D",
                  "8 Núcleos 16 Hilos • Hasta 5.0 GHz • 104MB Cache Total con 3D V-Cache • Socket AM5 PCIe 5.0", 5.0, 114),

            // Teclados & Mouse
            crear("Teclado Mecánico Custom Keychron Q1 Pro Wireless", "Keychron Q1 Pro", "perifericos", "Teclados & Mouse", "Keychron",
                  799.00, 899.00, "https://images.unsplash.com/photo-1587829741301-dc798b83add3?auto=format&fit=crop&w=600&q=80",
                  "Aluminio CNC", "indigo", 14, "KEY-Q1-PRO-RED",
                  "Estructura 75% CNC de Aluminio • Switches Gateron Jupiter Red Hotswap • Bluetooth 5.1 & USB-C", 4.8, 56),

            crear("Mouse Gamer Razer DeathAdder V3 Pro Wireless", "Razer DeathAdder V3 Pro", "perifericos", "Teclados & Mouse", "Razer",
                  529.00, 599.00, "https://images.unsplash.com/photo-1615663245857-ac93bb7c39e7?auto=format&fit=crop&w=600&q=80",
                  "Ultraligero 63g", "emerald", 30, "RZR-DA-V3-PRO",
                  "Sensor Óptico Focus Pro 30K • Switches Ópticos Gen-3 • Batería hasta 90 horas • Cable Speedflex Type C", 4.9, 78),

            // Audio & Streaming
            crear("Auriculares Inalámbricos SteelSeries Arctis Nova Pro", "Arctis Nova Pro Wireless", "audio", "Audio & Streaming", "SteelSeries",
                  1399.00, 1599.00, "https://images.unsplash.com/photo-1546435770-a3e426bf472b?auto=format&fit=crop&w=600&q=80",
                  "Cancelación de Ruido Activa", "indigo", 10, "SS-ARCTIS-NOVA-PRO",
                  "Drivers Hi-Res de Alta Fidelidad • Cancelación Activa de Ruido (ANC) de 4 micrófonos • Estación Base Dual USB", 4.8, 35)
        );

        productRepository.saveAll(seed);
        System.out.println(">> [Product-Service] ¡" + seed.size() + " productos sembrados exitosamente en la base de datos!");
    }

    private Product crear(String name, String shortName, String category, String categoryLabel, String brand,
                          Double price, Double originalPrice, String image, String tag, String tagColor,
                          Integer stock, String sku, String description, Double rating, Integer reviewsCount) {
        Product p = new Product();
        p.setName(name);
        p.setShortName(shortName);
        p.setCategory(category);
        p.setCategoryLabel(categoryLabel);
        p.setBrand(brand);
        p.setPrice(price);
        p.setOriginalPrice(originalPrice);
        p.setImage(image);
        p.setTag(tag);
        p.setTagColor(tagColor);
        p.setStock(stock);
        p.setSku(sku);
        p.setDescription(description);
        p.setRating(rating);
        p.setReviewsCount(reviewsCount);
        p.setActive(true);
        return p;
    }
}
