package elder.ly.mobile.di

import elder.ly.mobile.data.repository.calendar.CalendarRepository
import elder.ly.mobile.data.repository.message.MessageRepository
import elder.ly.mobile.data.repository.proposal.ProposalRepository
import elder.ly.mobile.data.repository.specialtie.SpecialtieRepository
import elder.ly.mobile.data.repository.user.UserRepository
import elder.ly.mobile.domain.service.CalendarService
import elder.ly.mobile.domain.service.MessageService
import elder.ly.mobile.domain.service.ProposalService
import elder.ly.mobile.domain.service.SpecialtieService
import elder.ly.mobile.domain.service.UserService
import elder.ly.mobile.ui.viewmodel.ProfileViewModel
import elder.ly.mobile.data.Rest
import elder.ly.mobile.data.repository.specialtie.ISpecialtieRepository
import elder.ly.mobile.data.repository.specialtie.SpecialtieRepositoryLocalImpl
import elder.ly.mobile.data.repository.user.IUserRepository
import elder.ly.mobile.data.repository.user.UserRepositoryLocalImpl
import elder.ly.mobile.ui.viewmodel.ProfileDetailsViewModel
import elder.ly.mobile.ui.viewmodel.SearchResultViewModel
import elder.ly.mobile.ui.viewmodel.SearchViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single<CalendarService> {
        Rest.calendarService
    }

    single<CalendarRepository> {
        CalendarRepository(get())
    }

    single<MessageService> {
        Rest.messageService
    }

    single<MessageRepository> {
        MessageRepository(get())
    }

    single<ProposalService> {
        Rest.proposalService
    }

    single<ProposalRepository> {
       ProposalRepository(get())
    }

    single<SpecialtieService> {
        Rest.specialtieService
    }

    single<ISpecialtieRepository> {
//        SpecialtieRepositoryLocalImpl(get())
        SpecialtieRepository(get())
    }

    single<UserService> {
        Rest.userService
    }

    single<IUserRepository> {
        UserRepository(get())
//        UserRepositoryLocalImpl(get())
    }

    viewModel{
        ProfileViewModel(get())
    }

    viewModel {
        ProfileDetailsViewModel(get())
    }

    viewModel {
        SearchResultViewModel(get())
    }

    viewModel {
        SearchViewModel(get())
    }
}