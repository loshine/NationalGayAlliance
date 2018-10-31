package xzy.loshine.nga.ui.image

import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import xzy.loshine.nga.di.scopes.ActivityScoped
import xzy.loshine.nga.di.scopes.FragmentScoped
import javax.inject.Named

@Module(includes = [ImagePreviewModule.FragmentModule::class])
class ImagePreviewModule {

    @Module
    interface FragmentModule {

        @FragmentScoped
        @ContributesAndroidInjector
        fun imagePreviewFragment(): ImagePreviewFragment
    }

    @ActivityScoped
    @Provides
    @Named("image_list")
    fun imageList(activity: ImagePreviewActivity): List<String> {
        return activity.intent.getStringArrayListExtra("image_list")
    }

    @ActivityScoped
    @Provides
    @Named("index")
    fun index(activity: ImagePreviewActivity): Int {
        return activity.intent.getIntExtra("index", 0)
    }
}