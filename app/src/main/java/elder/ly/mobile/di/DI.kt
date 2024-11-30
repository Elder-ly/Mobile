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
import elder.ly.mobile.data.repository.addresses.AddressRepository
import elder.ly.mobile.data.repository.addresses.IAddressRepository
import elder.ly.mobile.data.repository.auth.AuthRepository
import elder.ly.mobile.data.repository.auth.IAuthRepository
import elder.ly.mobile.data.repository.message.IMessageRepository
import elder.ly.mobile.ui.viewmodel.ChatListViewModel
import elder.ly.mobile.ui.viewmodel.ChatViewModel
import org.koin.core.module.dsl.viewModel
import elder.ly.mobile.ui.viewmodel.PersonalInfoViewModel
import elder.ly.mobile.ui.viewmodel.SignUpStepViewModel
import elder.ly.mobile.ui.viewmodel.ProfileViewModel
import elder.ly.mobile.data.repository.specialtie.ISpecialtieRepository
import elder.ly.mobile.data.repository.viacep.IViaCepRepository
import elder.ly.mobile.data.repository.viacep.ViaCepRepository
import elder.ly.mobile.domain.service.AddressService
import elder.ly.mobile.domain.service.AuthService
import elder.ly.mobile.domain.service.ViaCepService
import elder.ly.mobile.ui.viewmodel.AddressInfoViewModel
import elder.ly.mobile.ui.viewmodel.AuthViewModel
import elder.ly.mobile.ui.viewmodel.ProfessionalInfoViewModel
import elder.ly.mobile.ui.viewmodel.ProfileDetailsViewModel
import elder.ly.mobile.ui.viewmodel.ProposalViewModel
import elder.ly.mobile.ui.viewmodel.SearchResultViewModel
import elder.ly.mobile.ui.viewmodel.SearchViewModel
import elder.ly.mobile.ui.viewmodel.ViaCepViewModel
import org.koin.dsl.module

val appModule = module {

    single<AuthService> {
        Rest.authService
    }

    single<IAuthRepository>{
        AuthRepository(get())
    }

    single<AddressService> {
        Rest.addressService
    }

    single<IAddressRepository>{
        AddressRepository(get())
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
        SpecialtieRepository(get())
//        SpecialtieRepositoryLocalImpl(get())
    }

    single<UserService> {
        Rest.userService
    }

    single<IUserRepository> {
        UserRepository(get())
//        UserRepositoryLocalImpl(get())
    }

    single<ViaCepService> {
        Rest.viaCepService
    }

    single<IViaCepRepository> {
        ViaCepRepository(get())
    }

    viewModel {
        AuthViewModel(get())
    }

    viewModel{
        ProfileViewModel(get())
    }

    viewModel {
        ProfessionalInfoViewModel(get(), get())
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

    viewModel {
        ViaCepViewModel(get())
    }

    viewModel {
        ProposalViewModel(get())
    }
}