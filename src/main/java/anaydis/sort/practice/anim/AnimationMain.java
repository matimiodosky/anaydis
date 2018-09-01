package anaydis.sort.practice.anim;

import anaydis.sort.SorterProviderImpl;

class AnimationMain {

    public static void main(String[] args) {
        anaydis.animation.sort.gui.Main.animate(SorterProviderImpl.getInstance());
    }
}
