class State {

    interface ArtState {
        void validate();

        void prepareForOrder();

        void order();

    }

    interface Art {
        void setState(ArtState state);

        ArtState getState();

        String getType();

        void validate();

        void prepareForOrder();

        void order();

    }

    static class MyArt implements Art {
        private final String type;
        private ArtState state;

        public MyArt(String type) {
            this.type = type;
        }

        public ArtState getState() {
            return this.state;
        }

        public void setState(ArtState state) {
            this.state = state;
        }

        public String getType() {
            return this.type;
        }

        public void validate() {
            this.state.validate();
        }

        public void prepareForOrder() {
            this.state.prepareForOrder();
        }

        public void order() {
            this.state.order();
        }

    }

    static class NewArt implements ArtState {
        private final Art art;

        public NewArt(Art art) {
            this.art = art;
        }

        public void validate() {

            switch (art.getType()) {
            case "image":
                System.out.println("Validation sucessfull");
                art.setState(new ValidArt(this.art));
                break;
            case "print":
                System.out.println("Validation sucessfull, preparations for order unnecessary");
                art.setState(new ValidComplexArt(this.art));
                break;
            default:
                System.err.println("Validation fails");
                art.setState(new InvalidArt(this.art));
            }
        }

        public void prepareForOrder() {
            // do nothing
        }

        public void order() {
            // do nothing
        }
    }

    static class InvalidArt implements ArtState {
        private final Art art;

        public InvalidArt(Art art) {
            this.art = art;
        }

        public void validate() {
            // do nothing
        }

        public void prepareForOrder() {
            System.err.println("Invalid art could not be prepared for order");
            // do nothing
        }

        public void order() {
            System.err.println("Invalid art could not ordered");
            // do nothing
        }
    }

    static class ValidArt implements ArtState {
        private final Art art;

        public ValidArt(Art art) {
            this.art = art;
        }

        public void validate() {
            // do nothing
        }

        public void prepareForOrder() {
            System.out.println("Preparation for order done");
            art.setState(new AssetGeneratedArt(this.art));
        }

        public void order() {
            // do nothing
        }
    }

    static class ValidComplexArt implements ArtState {
        private final Art art;

        public ValidComplexArt(Art art) {
            this.art = art;
        }

        public void validate() {
            // do nothing
        }

        public void prepareForOrder() {
            // do nothing
        }

        public void order() {
            System.out.println("Art was ordered");
            this.art.setState(new OrderedArt(this.art));
        }
    }

    static class AssetGeneratedArt implements ArtState {
        private final Art art;

        public AssetGeneratedArt(Art art) {
            this.art = art;
        }

        public void validate() {
            // do nothing
        }

        public void prepareForOrder() {
            // do nothing
        }

        public void order() {
            System.out.println("Art was ordered");
            this.art.setState(new OrderedArt(this.art));
        }

    }

    static class OrderedArt implements ArtState {
        private final Art art;

        public OrderedArt(Art art) {
            this.art = art;
        }

        public void validate() {
            // do nothing
        }

        public void prepareForOrder() {
            // do nothing
        }

        public void order() {
            // do nothing
        }
    }

    public static final void main(String... args) {
        testImage();
        testPrint();
        testErr();
    }

    public static void testImage() {
        System.out.println("Valid image flow:");
        Art art = new MyArt("image");
        art.setState(new NewArt(art));
        art.validate();
        if (!(art.getState() instanceof ValidArt))
            throw new IllegalStateException("State should be " + ValidArt.class.getSimpleName() + " but was "
                    + art.getState().getClass().getSimpleName());

        art.prepareForOrder();
        if (!(art.getState() instanceof AssetGeneratedArt))
            throw new IllegalStateException("State should be " + AssetGeneratedArt.class.getSimpleName() + " but was "
                    + art.getState().getClass().getSimpleName());
        art.order();
        if (!(art.getState() instanceof OrderedArt))
            throw new IllegalStateException("State should be " + OrderedArt.class.getSimpleName() + " but was "
                    + art.getState().getClass().getSimpleName());
        System.out.println();
    }

    public static void testPrint() {
        System.out.println("Valid print flow:");
        Art art = new MyArt("print");
        art.setState(new NewArt(art));
        art.validate();
        if (!(art.getState() instanceof ValidComplexArt))
            throw new IllegalStateException("State should be " + ValidComplexArt.class.getSimpleName() + " but was "
                    + art.getState().getClass().getSimpleName());

        art.prepareForOrder();
        if (!(art.getState() instanceof ValidComplexArt))
            throw new IllegalStateException("State should be " + ValidComplexArt.class.getSimpleName() + " but was "
                    + art.getState().getClass().getSimpleName());
        art.order();
        if (!(art.getState() instanceof OrderedArt))
            throw new IllegalStateException("State should be " + OrderedArt.class.getSimpleName() + " but was "
                    + art.getState().getClass().getSimpleName());
        System.out.println();
    }

    public static void testErr() {
        System.out.println("Invalid type flow:");
        Art art = new MyArt("err_type");
        art.setState(new NewArt(art));
        art.validate();
        if (!(art.getState() instanceof InvalidArt))
            throw new IllegalStateException("State should be " + InvalidArt.class.getSimpleName() + " but was "
                    + art.getState().getClass().getSimpleName());

        art.prepareForOrder();
        if (!(art.getState() instanceof InvalidArt))
            throw new IllegalStateException("State should be " + InvalidArt.class.getSimpleName() + " but was "
                    + art.getState().getClass().getSimpleName());
        art.order();
        if (!(art.getState() instanceof InvalidArt))
            throw new IllegalStateException("State should be " + InvalidArt.class.getSimpleName() + " but was "
                    + art.getState().getClass().getSimpleName());
        System.out.println();

    }
}
