import java.util.ArrayList;
import java.util.List;

class MVC {
    static interface OnProductsChange {
        void onChange(List<String> products);
    }

    static class ProductModel {
        private List<String> products = new ArrayList<>();
        private List<OnProductsChange> listeners = new ArrayList<>();

        public void registerListener(OnProductsChange listener) {
            listeners.add(listener);
        }

        public void addNewProduct(String name) {
            this.products.add(name);
            notifyAllRegistered();
        }

        private void notifyAllRegistered() {
            for (OnProductsChange listener : this.listeners) {
                listener.onChange(this.products);
            }
        }
    }

    static class StarProductView implements OnProductsChange {
        private ProductController controller;

        public StarProductView(ProductController controller) {
            this.controller = controller;
        }

        public void onChange(List<String> products) {
            for (String product : products)
                System.out.println("***" + product);
        }

        public void addNewProduct(String name) {
            this.controller.addNewProduct(name);
        }
    }

    static class DashProductView implements OnProductsChange {
        private ProductController controller;

        public DashProductView(ProductController controller) {
            this.controller = controller;
        }

        public void onChange(List<String> products) {
            for (String product : products)
                System.out.println("---" + product);
        }

        public void addNewProduct(String name) {
            this.controller.addNewProduct(name);
        }
    }

    static class ProductController {
        private ProductModel productModel;

        public ProductController(ProductModel productModel) {
            this.productModel = productModel;
        }

        void addNewProduct(String product) {
            this.productModel.addNewProduct(product);
        }
    }

    public static void main(String[] args) {
        ProductModel model = new ProductModel();
        ProductController controller = new ProductController(model);
        StarProductView starView = new StarProductView(controller);
        DashProductView dashView = new DashProductView(controller);
        model.registerListener(starView);
        model.registerListener(dashView);
        starView.addNewProduct("111");
        dashView.addNewProduct("222");
    }
}