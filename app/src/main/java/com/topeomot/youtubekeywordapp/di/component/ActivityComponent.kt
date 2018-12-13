package com.topeomot.youtubekeywordapp.di.component

import com.topeomot.youtubekeywordapp.ui.main.MainActivity
import com.topeomot.youtubekeywordapp.di.ActivityScope
import com.topeomot.youtubekeywordapp.di.module.ActivityModule
import com.topeomot.youtubekeywordapp.ui.home.HomeFragment
import com.topeomot.youtubekeywordapp.ui.main.MainFragment
import dagger.Component

@ActivityScope
@Component(modules = [ActivityModule::class], dependencies = [ApplicationComponent::class])
interface ActivityComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainFragment: MainFragment)
    fun inject(homeFragment: HomeFragment)
}