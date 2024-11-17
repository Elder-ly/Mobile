package elder.ly.mobile.di

import elder.ly.mobile.data.repository.calendar.CalendarRepository
import elder.ly.mobile.data.repository.message.MessageRepository
import elder.ly.mobile.data.repository.proposal.ProposalRepository
import elder.ly.mobile.data.repository.specialtie.SpecialtieRepository
import elder.ly.mobile.data.repository.user.IUserRepository
import elder.ly.mobile.data.repository.user.UserRepository
import elder.ly.mobile.domain.service.CalendarService
import elder.ly.mobile.domain.service.MessageService
import elder.ly.mobile.domain.service.ProposalService
import elder.ly.mobile.domain.service.SpecialtieService
import elder.ly.mobile.domain.service.UserService
import elder.ly.mobile.data.Rest
import elder.ly.mobile.data.repository.addresses.AddressesRepository
import elder.ly.mobile.data.repository.addresses.IAddressesRepository
import elder.ly.mobile.data.repository.message.IMessageRepository
import elder.ly.mobile.ui.viewmodel.ChatListViewModel
import elder.ly.mobile.ui.viewmodel.ChatViewModel
import org.koin.core.module.dsl.viewModel
import elder.ly.mobile.ui.viewmodel.PersonalInfoViewModel
import elder.ly.mobile.ui.viewmodel.SignUpStepViewModel
import elder.ly.mobile.ui.viewmodel.ProfileViewModel
import elder.ly.mobile.data.repository.specialtie.ISpecialtieRepository
import elder.ly.mobile.domain.service.AddressesService
import elder.ly.mobile.ui.viewmodel.AddressInfoViewModel
import elder.ly.mobile.ui.viewmodel.ProfileDetailsViewModel
import elder.ly.mobile.ui.viewmodel.SearchResultViewModel
import elder.ly.mobile.ui.viewmodel.SearchViewModel
import org.koin.dsl.module

val appModule = module {

    single<AddressesService> {
        Rest.addressesService
    }

    single<IAddressesRepository>{
        AddressesRepository(get())
    }

    single<CalendarService> {
        Rest.calendarService
    }

    single<CalendarRepository> {
        CalendarRepository(get())
    }

    single<MessageService> {
        Rest.messageService
    }

    single<IMessageRepository> {
        MessageRepository(get())
        //  MessageRepositoryLocal(get())
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

    viewModel {
        SignUpStepViewModel(get())
    }

    viewModel {
        PersonalInfoViewModel(get())
    }

    viewModel {
        AddressInfoViewModel(get())
    }

    viewModel {
        ChatListViewModel(get())
    }

    viewModel {
        ChatViewModel(get())
    }
}